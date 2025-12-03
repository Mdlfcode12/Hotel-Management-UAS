/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.dao;
import hotel.entity.Reservasi;
import hotel.koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author MyBook Z Series
 */
public class UserDAO {
    private Connection conn;
 
	public UserDAO() {
    	conn = Koneksi.getKoneksi();
	}
        
        // --- LOGIKA BISNIS: Hitung Total Harga ---
	public int hitungTotalBayar(Reservasi r) {
    	long selisihMS = Math.abs(r.getTglCheckOut().getTime() - r.getTglCheckIn().getTime());
    	long selisihHari = TimeUnit.DAYS.convert(selisihMS, TimeUnit.MILLISECONDS);
    	
    	// Aturan: Jika checkin & checkout hari sama, dihitung 1 hari
    	if (selisihHari == 0) selisihHari = 1;
    	
    	// Total = Lama Inap * Harga Kamar
        return (int) (selisihHari * r.getHargaPerMalam());
	}
 
	// --- LOGIKA DATABASE: Simpan Reservasi ---
	public boolean simpanReservasi(Reservasi r) {
    	String sql = "INSERT INTO tabel_reservasi VALUES (?, ?, ?, ?, ?, ?, ?)";
    	try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	pst.setString(1, r.getIdReservasi());
        	
        	// PENTING: Konversi java.util.Date ke java.sql.Date
        	pst.setDate(2, new java.sql.Date(r.getTglCheckIn().getTime()));
        	pst.setDate(3, new java.sql.Date(r.getTglCheckOut().getTime()));
        	
        	pst.setString(4, r.getNikTamu());
        	pst.setString(5, r.getKodeKamar());
        	pst.setInt(6, r.getTotalBayar());
        	pst.setInt(7, r.getIdUser()); // ID User admin yg login
                
                pst.execute();
        	
        	// Opsional: Update status kamar jadi 'Terisi'
        	String sqlUpdate = "UPDATE tabel_kamar SET status='Terisi' WHERE kode_kamar=?";
        	PreparedStatement pst2 = conn.prepareStatement(sqlUpdate);
        	pst2.setString(1, r.getKodeKamar());
        	pst2.execute();
        	
        	return true;
                	} catch (Exception e) {
        	System.out.println("Error Simpan Reservasi: " + e.getMessage());
        	return false;
    	}
	}




    
}
