package hotel.entity;
import java.util.Date;

public class Reservasi {
    private String idReservasi, namaTamu,Nik, kodeKamar, status;
    private Date tglCheckIn, tglCheckOut;
    private int totalBayar;

    public Reservasi() {}

    // GETTER & SETTER (Generate pakai Alt+Insert)
    public String getIdReservasi() { return idReservasi; }
    public void setIdReservasi(String idReservasi) { this.idReservasi = idReservasi; }

    public String getNamaTamu() { return namaTamu; }
    public void setNamaTamu(String namaTamu) { this.namaTamu = namaTamu; }
    
    public String getNik() { return Nik;}
    public void setNik(String Nik) {this.Nik = Nik;}

    public String getKodeKamar() { return kodeKamar; }
    public void setKodeKamar(String kodeKamar) { this.kodeKamar = kodeKamar; }

    public Date getTglCheckIn() { return tglCheckIn; }
    public void setTglCheckIn(Date tglCheckIn) { this.tglCheckIn = tglCheckIn; }

    public Date getTglCheckOut() { return tglCheckOut; }
    public void setTglCheckOut(Date tglCheckOut) { this.tglCheckOut = tglCheckOut; }

    public int getTotalBayar() { return totalBayar; }
    public void setTotalBayar(int totalBayar) { this.totalBayar = totalBayar; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}