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
    
    public void insert(Tamu t) {
        String sql = "INSERT INTO tamu (nama_tamu, no_ktp, no_hp, alamat) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getnama_tamu());
            ps.setString(2, t.getno_ktp());
            ps.setString(3, t.getno_hp());
            ps.setString(4, t.getalamat());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert gagal: " + e.getMessage());
        }
    }
    
    public void update(Tamu t) {
        String sql = "UPDATE tamu SET nama_tamu=?, no_ktp=?, no_hp=?, alamat=? WHERE id_tamu=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getnama_tamu());
            ps.setString(2, t.getno_ktp());
            ps.setString(3, t.getno_hp());
            ps.setString(4, t.getalamat());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update gagal: " + e.getMessage());
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM tamu WHERE id_tamu=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete gagal: " + e.getMessage());
        }
    }
    
    public List<Tamu> getAll() {
        List<Tamu> list = new ArrayList<>();
        String sql = "SELECT * FROM tamu";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Tamu t = new Tamu();
                t.setnama_tamu(rs.getString("nama_tamu"));
                t.setno_ktp(rs.getString("no_ktp"));
                t.setno_hp(rs.getString("no_hp"));
                t.setalamat(rs.getString("alamat"));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Get data gagal: " + e.getMessage());
        }
        return list;
    }
}
