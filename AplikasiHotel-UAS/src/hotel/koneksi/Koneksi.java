package hotel.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    private static Connection mysqlconfig;
    
    public static Connection configDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/db_hotel_uas"; //url database
            String user = "root"; //user database
            String pass = ""; //password database
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + e.getMessage());
        }
        return mysqlconfig;
    }
}