package hotel.dao;

import hotel.entity.User;
import hotel.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    
    public boolean prosesLogin(User u) {
        boolean berhasil = false;
        String sql = "SELECT * FROM tabel_user WHERE username=? AND password=?";
        
        try {
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getPassword());
            ResultSet res = pst.executeQuery();
            
            if(res.next()) {
                berhasil = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return berhasil;
    }
    public boolean tambahUser(User u) {
        String sql = "INSERT INTO tabel_user (username, password, nama_lengkap) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getNamaLengkap()); // Pastikan di Entity User sudah ada Getter/Setter namaLengkap
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Tambah: " + e.getMessage());
            return false;
        }
    }
      public boolean ubahUser(User u) {
        String sql = "UPDATE tabel_user SET username=?, password=?, nama_lengkap=? WHERE id_user=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getNamaLengkap());
            pst.setInt(4, u.getIdUser()); // Butuh ID untuk where clause
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error Update: " + e.getMessage());
            return false;
        }
      }
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
        public List<User> getSemuaUser() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_user";
        try {
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                User u = new User();
                u.setIdUser(res.getInt("id_user"));
                u.setUsername(res.getString("username"));
                u.setPassword(res.getString("password"));
                u.setNamaLengkap(res.getString("nama_lengkap"));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error Get: " + e.getMessage());
        }
        return list;
    }
}