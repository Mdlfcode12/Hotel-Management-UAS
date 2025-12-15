package hotel.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection mysqlconfig;
    
    public static Connection getKoneksi() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_hotel_uas"; // Nama Database
            String user = "root"; // User XAMPP
            String pass = ""; // Password XAMPP
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            
        } catch (SQLException e) {
            System.err.println("Koneksi Gagal: " + e.getMessage());
        }
        
        // --- INI BAGIAN PENTINGNYA ---
        // Jangan ada 'throw new Unsupported...', tapi harus return connection
        return mysqlconfig; 
    }
}