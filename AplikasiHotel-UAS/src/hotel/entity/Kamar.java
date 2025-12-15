/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.entity;

/**
 *
 * @author MyBook Z Series
 */
public class Kamar {
    private String kodeKamar;
	private String tipeKamar;
	private int harga;
	private String status;
 
 
	// Getter & Setter (Generate otomatis di NetBeans: Alt+Insert)
	public String getKodeKamar() {
            return kodeKamar; 
        
        }
	public void setKodeKamar(String kodeKamar) { 
            this.kodeKamar = kodeKamar;
        }
 
	public String getTipeKamar() { 
            return tipeKamar; 
        }
        
	public void setTipeKamar(String tipeKamar) {
            this.tipeKamar = tipeKamar; 
        }
 
	public int getHarga() {
            return harga; 
        }
        
	public void setHarga(int harga) { 
            this.harga = harga; 
        }
 
	public String getStatus() { 
            return status; 
        }
        
	public void setStatus(String status) { 
            this.status = status; 
        }

}
