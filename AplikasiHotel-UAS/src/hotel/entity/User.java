package hotel.entity;

public class User {
    private int id_user;
    private String  username, password, nama_lengkap;

    
    public int getid_user() { 
        return id_user; 
    }
    
    public void setid_user(int id_user) { 
        this.id_user = id_user;
    }
    
    public String getUsername() { 
        return username; 
    }
    
    public void setusername(String username) { 
        this.username = username;
    }
    
    public String getPassword() { 
        return password; 
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getnama_lengkap() { 
        return nama_lengkap; 
    }
    
    public void setnama_lengkap(String nama_lengkap) { 
        this.nama_lengkap = nama_lengkap;
    }
}
