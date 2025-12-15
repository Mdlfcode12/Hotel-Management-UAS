/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.dao;
import hotel.entity.Tamu;
import hotel.koneksi.Koneksi;
import java.sql.Connection;
/**
 *
 * @author ACER
 */
public class TamuDAO {
    private Connection conn;
    
    public TamuDAO(){
        conn = Koneksi.getKoneksi();
    }
    
 
}
