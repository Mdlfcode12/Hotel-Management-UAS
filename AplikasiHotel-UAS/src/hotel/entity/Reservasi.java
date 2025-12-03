/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.entity;
import java.util.Date;
/**
 *
 * @author MyBook Z Series
 */
public class Reservasi {
    private String idReservasi;
	private Date tglCheckIn;  // Tipe data Date
	private Date tglCheckOut; // Tipe data Date
	private String nikTamu;
	private String kodeKamar;
	private int totalBayar;
	private int idUser;
	
	// Variabel bantuan (tidak masuk DB, cuma untuk transfer data harga)
	private int hargaPerMalam;
 
	public Reservasi () {}
 
	// GETTER & SETTER (Alt+Insert > Getter and Setter > Select All)
	public String getIdReservasi() { return idReservasi; }
	public void setIdReservasi(String idReservasi) { this.idReservasi = idReservasi; }
 
	public Date getTglCheckIn() { return tglCheckIn; }
	public void setTglCheckIn(Date tglCheckIn) { this.tglCheckIn = tglCheckIn; }
 
	public Date getTglCheckOut() { return tglCheckOut; }
	public void setTglCheckOut(Date tglCheckOut) { this.tglCheckOut = tglCheckOut; }
 
	public String getNikTamu() { return nikTamu; }
	public void setNikTamu(String nikTamu) { this.nikTamu = nikTamu; }
 
	public String getKodeKamar() { return kodeKamar; }
	public void setKodeKamar(String kodeKamar) { this.kodeKamar = kodeKamar; }
 
	public int getTotalBayar() { return totalBayar; }
	public void setTotalBayar(int totalBayar) { this.totalBayar = totalBayar; }
 
	public int getIdUser() { return idUser; }
	public void setIdUser(int idUser) { this.idUser = idUser; }
 
	public int getHargaPerMalam() { return hargaPerMalam; }
	public void setHargaPerMalam(int hargaPerMalam) { this.hargaPerMalam = hargaPerMalam; }


}
