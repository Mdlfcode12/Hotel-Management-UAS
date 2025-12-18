package hotel.dao;

import hotel.entity.Reservasi;
import hotel.koneksi.Koneksi;
import java.sql.*;
import java.util.concurrent.TimeUnit; // Untuk hitung selisih hari

public class ReservasiDAO {
    private Connection conn;

    public ReservasiDAO() {
        conn = Koneksi.getKoneksi();
    }

    // --- FITUR 1: AMBIL HARGA KAMAR (Untuk Hitung Total) ---
    public int getHargaKamar(String kodeKamar) {
        int harga = 0;
        try {
            String sql = "SELECT harga FROM tabel_kamar WHERE kode_kamar=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kodeKamar);
            ResultSet res = pst.executeQuery();
            if(res.next()) {
                harga = res.getInt("harga");
            }
        } catch (Exception e) {
            System.out.println("Error Get Harga: " + e.getMessage());
        }
        return harga;
    }

    // --- FITUR 2: SIMPAN RESERVASI ---
    public boolean simpanReservasi(Reservasi r) {
        // Query Insert Data
        String sql = "INSERT INTO tabel_reservasi (id_reservasi, nama_tamu, kode_kamar, tgl_checkin, tgl_checkout, total_bayar, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, r.getIdReservasi());
            pst.setString(2, r.getNamaTamu());
            pst.setString(3, r.getKodeKamar());
            
            // Konversi Date Java ke Date SQL
            pst.setDate(4, new java.sql.Date(r.getTglCheckIn().getTime()));
            pst.setDate(5, new java.sql.Date(r.getTglCheckOut().getTime()));
            
            pst.setInt(6, r.getTotalBayar());
            pst.setString(7, "Booking");
            
            pst.execute();
            
            // --- PENTING: UPDATE STATUS KAMAR JADI 'TERISI' ---
            String sqlUpdate = "UPDATE tabel_kamar SET status='Terisi' WHERE kode_kamar=?";
            PreparedStatement pstKamar = conn.prepareStatement(sqlUpdate);
            pstKamar.setString(1, r.getKodeKamar());
            pstKamar.execute();
            
            return true;
        } catch (Exception e) {
            System.out.println("Error Simpan Reservasi: " + e.getMessage());
            return false;
        }
    }
    
    // --- FITUR 3: HITUNG SELISIH HARI ---
    public long hitungLamaInap(java.util.Date checkIn, java.util.Date checkOut) {
        long selisihWaktu = Math.abs(checkOut.getTime() - checkIn.getTime());
        long selisihHari = TimeUnit.DAYS.convert(selisihWaktu, TimeUnit.MILLISECONDS);
        // Jika checkin & checkout hari sama, dihitung 1 hari
        if (selisihHari == 0) selisihHari = 1; 
        return selisihHari;
    }
}