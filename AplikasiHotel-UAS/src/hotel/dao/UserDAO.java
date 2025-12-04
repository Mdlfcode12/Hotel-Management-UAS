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
}