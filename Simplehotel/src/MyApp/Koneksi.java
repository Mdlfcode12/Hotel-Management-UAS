/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    // Buat satu variabel koneksi
    private static Connection koneksi;

    // Buat fungsi untuk mengambil koneksi
    public static Connection getKoneksi() {
        // Cek apakah koneksi null atau sudah ditutup
        try {
            if (koneksi == null || koneksi.isClosed()) {
                // Jika ya, buat koneksi baru
                String url = "jdbc:mysql://localhost:3306/db_hotel"; // Nama database Anda
                String user = "root"; // User default XAMPP
                String password = ""; // Password default XAMPP

                // Daftarkan driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                
                // Buat koneksi
                koneksi = DriverManager.getConnection(url, user, password);
                
                System.out.println("Koneksi Database Berhasil.");
            }
        } catch (SQLException e) {
            // Tangani error jika koneksi gagal
            JOptionPane.showMessageDialog(null, "Koneksi ke database gagal: " + e.getMessage());
            System.out.println("Koneksi Database Gagal: " + e.getMessage());
        }
        
        // Kembalikan koneksi yang sudah ada atau yang baru dibuat
        return koneksi;
    }

    // (Opsional) Fungsi main untuk tes koneksi
    public static void main(String[] args) {
        // Panggil fungsi getKoneksi() untuk menguji
        Connection c = Koneksi.getKoneksi();
        
        // Cek apakah koneksi berhasil didapat
        if (c != null) {
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil!");
        } else {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal!");
        }
    }
}
