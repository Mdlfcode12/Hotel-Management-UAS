package hotel.dao;

import hotel.entity.Kamar;
import hotel.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KamarDAO {
    
    private Connection conn;

    public KamarDAO() {
        conn = Koneksi.getKoneksi();
    }

    // Method INSERT
    public boolean tambahKamar(Kamar k) {
        String sql = "INSERT INTO tabel_kamar VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, k.getKodeKamar());
            pst.setString(2, k.getTipeKamar());
            pst.setInt(3, k.getHarga());
            pst.setString(4, k.getStatus());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Tambah: " + e.getMessage());
            return false;
        }
    }
    // method Ubah
    public boolean ubahKamar(Kamar k) {
        String sql = "UPDATE tabel_kamar SET tipe_kamar=?, harga=?, status=? WHERE kode_kamar=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, k.getTipeKamar());
            pst.setInt(2, k.getHarga());
            pst.setString(3, k.getStatus());
            pst.setString(4, k.getKodeKamar()); // Butuh ID untuk where clause
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Update: " + e.getMessage());
            return false;
        }
    }

    // Method DELETE
    public boolean hapusKamar(String kode) {
        String sql = "DELETE FROM tabel_kamar WHERE kode_kamar=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kode);
            pst.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Method READ (Mengambil semua data untuk ditampilkan di tabel)
    public List<Kamar> getSemuaKamar() {
        List<Kamar> listKamar = new ArrayList<>();
        String sql = "SELECT * FROM tabel_kamar";
        try {
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                Kamar k = new Kamar();
                k.setKodeKamar(res.getString("kode_kamar"));
                k.setTipeKamar(res.getString("tipe_kamar"));
                k.setHarga(res.getInt("harga"));
                k.setStatus(res.getString("status"));
                listKamar.add(k); // Masukkan objek ke List
            }
        } catch (Exception e) {
            System.out.println("Error Get: " + e.getMessage());
        }
        return listKamar;
    }
    // Tambahkan method ini di dalam class KamarDAO yang sudah ada
    public int getHargaKamar(String kode) {
    int harga = 0;
    String sql = "SELECT harga FROM tabel_kamar WHERE kode_kamar=?";
    try {
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, kode);
        ResultSet res = pst.executeQuery();
        if(res.next()) {
            harga = res.getInt("harga");
        }
    } catch (Exception e) {
        System.out.println("Error Cek Harga: " + e.getMessage());
    }
    return harga;
}


}