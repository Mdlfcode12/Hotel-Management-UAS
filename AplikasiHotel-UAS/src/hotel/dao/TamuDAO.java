/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.dao;
import hotel.entity.Tamu;
import hotel.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ACER
 */
public class TamuDAO {
    private Connection conn;
    
    public TamuDAO(){
        conn = Koneksi.getKoneksi();
    }
    
    // 1. Simpan Data
    public boolean simpanTamu(Tamu t) {
        String sql = "INSERT INTO tabel_tamu VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.getno_ktp());
            pst.setString(2, t.getnama_tamu());
            pst.setString(3, t.getno_hp());
            pst.setString(4, t.getalamat());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Simpan: " + e.getMessage());
            return false;
        }
    }

    // 2. Ubah Data
    public boolean ubahTamu(Tamu t) {
        String sql = "UPDATE tabel_tamu SET nama_tamu=?, no_telp=?, alamat=? WHERE nik=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.getnama_tamu());
            pst.setString(2, t.getno_hp());
            pst.setString(3, t.getalamat());
            pst.setString(4, t.getno_ktp()); // Where clause
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Ubah: " + e.getMessage());
            return false;
        }
    }

    // 3. Hapus Data
    public boolean hapusTamu(String nik) {
        String sql = "DELETE FROM tabel_tamu WHERE nik=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nik);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Hapus: " + e.getMessage());
            return false;
        }
    }

    // 4. Ambil Semua Data (Untuk Tabel)
    public List<Tamu> getAllTamu() {
        List<Tamu> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_tamu";
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                Tamu t = new Tamu();
                t.setno_ktp(res.getString("nik"));
                t.setnama_tamu(res.getString("nama_tamu"));
                t.setno_hp(res.getString("no_telp"));
                t.setalamat(res.getString("alamat"));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error Get: " + e.getMessage());
        }
        return list;
    }
    //ambil data tamu untuk di reservasi
    public java.util.List<String> getDaftarNamaTamu() {
        java.util.List<String> listNama = new java.util.ArrayList<>();
        // Kita urutkan nama dari A-Z agar mudah dicari
        String sql = "SELECT nama_tamu FROM tabel_tamu ORDER BY nama_tamu ASC";
        
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                listNama.add(res.getString("nama_tamu"));
            }
        } catch (Exception e) {
            System.out.println("Error Ambil Nama: " + e.getMessage());
        }
        return listNama;
    }
}
