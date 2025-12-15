package hotel.entity;
public class Tamu {
    private String nama_tamu, no_ktp, no_hp, alamat;
    
        public String getnama_tamu() {
            return nama_tamu; 
        }
	public void setnama_tamu(String nama_tamu) { 
            this.nama_tamu = nama_tamu;
        }
 
	public String getno_ktp() { 
            return no_ktp; 
        }
        
	public void setno_ktp(String no_ktp) {
            this.no_ktp = no_ktp; 
        }
 
	public String getno_hp() {
            return no_hp; 
        }
        
	public void setno_hp(String no_hp) { 
            this.no_hp = no_hp; 
        }
 
	public String getalamat() { 
            return alamat; 
        }
        
	public void setalamat(String alamat) { 
            this.alamat = alamat; 
        }
}
