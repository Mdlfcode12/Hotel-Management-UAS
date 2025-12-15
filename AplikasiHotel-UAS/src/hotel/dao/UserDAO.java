package hotel.dao;

import hotel.entity.User;
import hotel.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
     private Connection conn;

    public UserDAO() {
        conn = Koneksi.getKoneksi();
    }

    // --- 1. FUNGSI LOGIN (Yang sudah ada) ---
    public boolean prosesLogin(User u) {
        // ... (kode login sebelumnya) ...
        return false; // placeholder
    }

    // --- 2. FUNGSI INSERT (Tambah User Baru) ---
    public boolean tambahUser(User u) {
        String sql = "INSERT INTO tabel_user (username, password, nama_lengkap) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getnama_lengkap()); // Pastikan di Entity User sudah ada Getter/Setter namaLengkap
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Tambah: " + e.getMessage());
            return false;
        }
    }

    // --- 3. FUNGSI UPDATE (Edit User) ---
    public boolean ubahUser(User u) {
        String sql = "UPDATE tabel_user SET username=?, password=?, nama_lengkap=? WHERE id_user=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getnama_lengkap());
            pst.setInt(4, u.getid_user()); // Butuh ID untuk where clause
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Update: " + e.getMessage());
            return false;
        }
    }

    // --- 4. FUNGSI DELETE (Hapus User) ---
    public boolean hapusUser(int id) {
        String sql = "DELETE FROM tabel_user WHERE id_user=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // --- 5. FUNGSI SELECT ALL (Tampilkan di Tabel) ---
    public List<User> getSemuaUser() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_user";
        try {
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                User u = new User();
                u.setid_user(res.getInt("id_user"));
                u.setusername(res.getString("username"));
                u.setPassword(res.getString("password"));
                u.setnama_lengkap(res.getString("nama_lengkap"));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error Get: " + e.getMessage());
        }
        return list;
    }
}