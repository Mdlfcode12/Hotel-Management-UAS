package hotel.dao;

import hotel.entity.Reservasi;
import hotel.koneksi.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;// Untuk hitung selisih hari

public class ReservasiDAO {
    private Connection conn;

    public ReservasiDAO() {
        conn = Koneksi.getKoneksi();
    }

    // --- FITUR 1: AMBIL HARGA KAMAR (Untuk Hitung Total) ---
    // --- 1. SIMPAN (Sesuai request: Pakai Nama Tamu) ---
    public boolean simpanReservasi(Reservasi r) {
        String sql = "INSERT INTO tabel_reservasi VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, r.getIdReservasi());
            pst.setString(2, r.getNik());
            pst.setString(3, r.getNamaTamu());
            pst.setString(4, r.getKodeKamar());
            pst.setDate(5, new java.sql.Date(r.getTglCheckIn().getTime()));
            pst.setDate(6, new java.sql.Date(r.getTglCheckOut().getTime()));
            pst.setInt(7, r.getTotalBayar());
            pst.setString(8, "Check In");
            pst.execute();
            
            // Update Status Kamar
            String sqlKam = "UPDATE tabel_kamar SET status='Terisi' WHERE kode_kamar=?";
            PreparedStatement pstKam = conn.prepareStatement(sqlKam);
            pstKam.setString(1, r.getKodeKamar());
            pstKam.execute();
            
            return true;
        } catch (Exception e) {
            System.out.println("Error Simpan: " + e.getMessage());
            return false;
        }
    }

    // --- 2. AMBIL DATA UNTUK TABEL ---
    public List<Reservasi> getAllReservasi() {
        List<Reservasi> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_reservasi ORDER BY id_reservasi DESC";
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                Reservasi r = new Reservasi();
                r.setIdReservasi(res.getString("id_reservasi"));
                r.setNamaTamu(res.getString("nama_tamu")); // Ambil Nama
                r.setNik(res.getString("nik"));
                r.setKodeKamar(res.getString("kode_kamar"));
                r.setTglCheckIn(res.getDate("tgl_checkin"));
                r.setTglCheckOut(res.getDate("tgl_checkout"));
                r.setTotalBayar(res.getInt("total_bayar"));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println("Error Get Table: " + e.getMessage());
        }
        return list;
    }

    // --- 3. HITUNG DURASI & AMBIL HARGA (Tetap sama) ---
    public int getHargaKamar(String kode) {
        int harga = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT harga FROM tabel_kamar WHERE kode_kamar=?");
            pst.setString(1, kode);
            ResultSet res = pst.executeQuery();
            if(res.next()) harga = res.getInt("harga");
        } catch (Exception e) {}
        return harga;
    }

    public long hitungLamaInap(java.util.Date in, java.util.Date out) {
        long diff = out.getTime() - in.getTime();
        long hari = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return (hari == 0) ? 1 : hari;
    }
    // --- FITUR CHECK-OUT (UPDATE STATUS) ---
    public boolean prosesCheckOut(String idReservasi, String kodeKamar) {
        try {
            // 1. Ubah Status Reservasi jadi 'Selesai'
            String sqlReservasi = "UPDATE tabel_reservasi SET status='Selesai' WHERE id_reservasi=?";
            PreparedStatement pst1 = conn.prepareStatement(sqlReservasi);
            pst1.setString(1, idReservasi);
            pst1.execute();
            
            // 2. Ubah Status Kamar jadi 'Kosong' (PENTING! Agar bisa dibooking lagi)
            String sqlKamar = "UPDATE tabel_kamar SET status='Tersedia' WHERE kode_kamar=?";
            PreparedStatement pst2 = conn.prepareStatement(sqlKamar);
            pst2.setString(1, kodeKamar);
            pst2.execute();
            
            return true;
        } catch (Exception e) {
            System.out.println("Error CheckOut: " + e.getMessage());
            return false;
        }
    }
}
