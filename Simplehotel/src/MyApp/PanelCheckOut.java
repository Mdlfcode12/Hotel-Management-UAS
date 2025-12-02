/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package MyApp;
import com.toedter.calendar.JDateChooser; // Import JDateChooser
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit; // Untuk menghitung selisih hari
import javax.swing.JOptionPane;
/**
 *
 * @author MyBook Z Series
 */
public class PanelCheckOut extends javax.swing.JPanel {
// HashMap untuk menyimpan data-data penting dari reservasi yang dipilih
    // Key: Tampilan di JComboBox (String), Value: ID Reservasi (Integer)
    private HashMap<String, Integer> reservasiMap;
    
    // Key: ID Reservasi (Integer), Value: Data terkait (Object)
    private HashMap<Integer, String> reservasiTglCheckInMap;
    private HashMap<Integer, Double> reservasiHargaMap;
    private HashMap<Integer, Integer> reservasiIdKamarMap;
    
    // Variabel untuk menyimpan data kalkulasi
    private long jumlahMalam = 0;
    private double totalTagihan = 0;
    private int idKamarDipilih = 0; // ID kamar yang akan di-update
    private int idReservasiDipilih = 0; // ID reservasi yang akan di-update
    
    public PanelCheckOut() {
        initComponents();
        // Inisialisasi semua HashMap
        reservasiMap = new HashMap<>();
        reservasiTglCheckInMap = new HashMap<>();
        reservasiHargaMap = new HashMap<>();
        reservasiIdKamarMap = new HashMap<>();
        
        
        // Set tanggal check-out hari ini
        dateCheckOut.setDate(new Date());
        
        // Muat data reservasi yang 'Aktif'
        loadDataReservasiAktif();
        
        // Atur status awal
        clearForm();
    }

    // Fungsi untuk memuat data reservasi yang statusnya 'Aktif'
    private void loadDataReservasiAktif() {
        Connection koneksi = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            koneksi = Koneksi.getKoneksi();
            stmt = koneksi.createStatement();
            
            // Query JOIN 3 tabel untuk mengambil data yg relevan
            String sql = "SELECT r.id_reservasi, r.id_kamar, r.tgl_checkin, t.nama_tamu, k.no_kamar, k.harga_per_malam " +
                         "FROM tabel_reservasi r " +
                         "JOIN tabel_tamu t ON r.id_tamu = t.id_tamu " +
                         "JOIN tabel_kamar k ON r.id_kamar = k.id_kamar " +
                         "WHERE r.status_reservasi = 'Aktif' " +
                         "ORDER BY t.nama_tamu ASC";
            
            rs = stmt.executeQuery(sql);

            // Bersihkan data lama
            cmbReservasi.removeAllItems();
            reservasiMap.clear();
            reservasiTglCheckInMap.clear();
            reservasiHargaMap.clear();
            reservasiIdKamarMap.clear();
            
            cmbReservasi.addItem("-- Pilih Reservasi --");

            while (rs.next()) {
                int idReservasi = rs.getInt("id_reservasi");
                int idKamar = rs.getInt("id_kamar");
                String namaTamu = rs.getString("nama_tamu");
                String noKamar = rs.getString("no_kamar");
                String tglCheckIn = rs.getString("tgl_checkin");
                double harga = rs.getDouble("harga_per_malam");
                
                // Teks yang akan tampil di JComboBox
                String displayText = "Tamu: " + namaTamu + " - Kamar: " + noKamar;
                
                // Isi JComboBox
                cmbReservasi.addItem(displayText);
                
                // Simpan semua data penting di HashMap
                reservasiMap.put(displayText, idReservasi);
                reservasiTglCheckInMap.put(idReservasi, tglCheckIn);
                reservasiHargaMap.put(idReservasi, harga);
                reservasiIdKamarMap.put(idReservasi, idKamar);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error memuat data reservasi: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }
    
    // Fungsi untuk membersihkan form
    private void clearForm() {
        if (cmbReservasi.getItemCount() > 0) {
        cmbReservasi.setSelectedIndex(0); // <--- SEKARANG AMAN
        }
        txtTglCheckIn.setText("");
        txtHarga.setText("");
        dateCheckOut.setDate(new Date()); // Set tanggal hari ini
        txtJumlahMalam.setText("");
        txtTotalTagihan.setText("");
        
        // Nonaktifkan tombol
        btnHitung.setEnabled(false);
        btnSimpanCheckOut.setEnabled(false);
        
        // Reset variabel
        jumlahMalam = 0;
        totalTagihan = 0;
        idKamarDipilih = 0;
        idReservasiDipilih = 0;   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHitung = new javax.swing.JButton();
        btnSimpanCheckOut = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cmbReservasi = new javax.swing.JComboBox<>();
        txtJumlahMalam = new javax.swing.JTextField();
        btnBatal = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTotalTagihan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTglCheckIn = new javax.swing.JTextField();
        dateCheckOut = new com.toedter.calendar.JDateChooser();
        txtHarga = new javax.swing.JTextField();

        btnHitung.setText("Hitung");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });

        btnSimpanCheckOut.setText("Simpan Check Out");
        btnSimpanCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanCheckOutActionPerformed(evt);
            }
        });

        jLabel6.setText("Jumlah Malam");

        cmbReservasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbReservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReservasiActionPerformed(evt);
            }
        });

        txtJumlahMalam.setEditable(false);

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel7.setText("Total Tagihan");

        jLabel4.setText("Harga/Malam");

        txtTotalTagihan.setEditable(false);

        jLabel3.setText("Tanggal Check-In");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulir Check Out Tamu");

        jLabel5.setText("Tanggal Check Out");

        jLabel2.setText("Pilih Reservasi (Tamu - Kamar)");

        txtTglCheckIn.setEditable(false);

        txtHarga.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpanCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtJumlahMalam, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotalTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTglCheckIn)
                                            .addComponent(cmbReservasi, 0, 354, Short.MAX_VALUE))))
                                .addGap(0, 9, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbReservasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTglCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(dateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHitung)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtJumlahMalam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTotalTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanCheckOut)
                    .addComponent(btnBatal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        if (dateCheckOut.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tanggal Check-Out wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // 1. Ambil tanggal Check-In (dari text field)
            String tglCheckInStr = txtTglCheckIn.getText();
            Date tglCheckIn = new SimpleDateFormat("yyyy-MM-dd").parse(tglCheckInStr);

            // 2. Ambil tanggal Check-Out (dari JDateChooser)
            Date tglCheckOut = dateCheckOut.getDate();

            // 3. Validasi tanggal
            if (tglCheckOut.before(tglCheckIn)) {
                JOptionPane.showMessageDialog(this, "Tanggal Check-Out tidak boleh sebelum Tanggal Check-In!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4. Hitung selisih hari (Jumlah Malam)
            long selisihMillis = tglCheckOut.getTime() - tglCheckIn.getTime();
            jumlahMalam = TimeUnit.MILLISECONDS.toDays(selisihMillis);

            // Logika bisnis: jika check-in dan check-out di hari yang sama, tetap dihitung 1 malam
            if (jumlahMalam == 0) {
                jumlahMalam = 1;
            }

            // 5. Ambil Harga per Malam
            double hargaPerMalam = Double.parseDouble(txtHarga.getText());

            // 6. Hitung Total Tagihan
            totalTagihan = hargaPerMalam * jumlahMalam;

            // 7. Tampilkan hasil
            txtJumlahMalam.setText(String.valueOf(jumlahMalam) + " Malam");
            txtTotalTagihan.setText("Rp " + String.format("%,.0f", totalTagihan)); // Format dengan pemisah ribuan

            // 8. Aktifkan tombol Simpan
            btnSimpanCheckOut.setEnabled(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi error saat menghitung: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnSimpanCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanCheckOutActionPerformed
        // 1. Validasi: Pastikan tagihan sudah dihitung
        if (totalTagihan == 0 || jumlahMalam == 0 || idKamarDipilih == 0 || idReservasiDipilih == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih reservasi dan klik 'Hitung Total' terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Ambil Tanggal Check-Out
        java.util.Date tglCheckOutUtil = dateCheckOut.getDate();
        java.sql.Date tglCheckOutSql = new java.sql.Date(tglCheckOutUtil.getTime());

        // 3. Proses ke Database (Transaction)
        Connection koneksi = null;
        PreparedStatement pstmt1 = null; // Update Reservasi
        PreparedStatement pstmt2 = null; // Update Kamar

        try {
            koneksi = Koneksi.getKoneksi();
            koneksi.setAutoCommit(false); // Mulai Transaction

            // QUERY 1: Update tabel_reservasi
            String sql1 = "UPDATE tabel_reservasi SET tgl_checkout = ?, total_biaya = ?, status_reservasi = 'Selesai' WHERE id_reservasi = ?";
            pstmt1 = koneksi.prepareStatement(sql1);
            pstmt1.setDate(1, tglCheckOutSql);
            pstmt1.setDouble(2, totalTagihan);
            pstmt1.setInt(3, idReservasiDipilih);
            int hasil1 = pstmt1.executeUpdate();

            // QUERY 2: Update tabel_kamar
            // (Kita set 'Tersedia'. Bisa juga 'Perlu Dibersihkan' jika ingin lebih kompleks)
            String sql2 = "UPDATE tabel_kamar SET status = 'Tersedia' WHERE id_kamar = ?";
            pstmt2 = koneksi.prepareStatement(sql2);
            pstmt2.setInt(1, idKamarDipilih);
            int hasil2 = pstmt2.executeUpdate();

            // 4. Selesaikan Transaksi (Commit)
            if (hasil1 > 0 && hasil2 > 0) {
                koneksi.commit(); // Simpan permanen kedua perubahan
                JOptionPane.showMessageDialog(this, "Check-Out berhasil disimpan. Total Tagihan: Rp " + String.format("%,.0f", totalTagihan));

                // Refresh form
                loadDataReservasiAktif();
                clearForm();

            } else {
                koneksi.rollback(); // Batalkan kedua perubahan
                JOptionPane.showMessageDialog(this, "Check-Out Gagal. Ada kesalahan pada database.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            try {
                if(koneksi != null) koneksi.rollback(); // Batalkan jika ada error SQL
            } catch (SQLException ex) {}
            JOptionPane.showMessageDialog(this, "Terjadi Error SQL: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } finally {
            try {
                if (koneksi != null) koneksi.setAutoCommit(true); // Kembalikan ke mode auto-commit
            } catch (SQLException e) {}
            try { if (pstmt1 != null) pstmt1.close(); } catch (Exception e) {}
            try { if (pstmt2 != null) pstmt2.close(); } catch (Exception e) {}
        }
    }//GEN-LAST:event_btnSimpanCheckOutActionPerformed

    private void cmbReservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReservasiActionPerformed
        // 1. Selalu bersihkan field dan nonaktifkan tombol saat pilihan berubah
        txtTglCheckIn.setText("");
        txtHarga.setText("");
        txtJumlahMalam.setText("");
        txtTotalTagihan.setText("");
        btnHitung.setEnabled(false);
        btnSimpanCheckOut.setEnabled(false);

        // 2. Cek apakah yang dipilih adalah item valid (bukan "-- Pilih Reservasi --")
        if (cmbReservasi.getSelectedIndex() > 0) {
            try {
                String selectedText = cmbReservasi.getSelectedItem().toString();

                // 3. Ambil ID sebagai Objek Integer (lebih aman dari null)
                Integer idReservasi = reservasiMap.get(selectedText);

                // 4. PERIKSA NULL PERTAMA: Apakah ID Reservasi ditemukan?
                if (idReservasi == null) {
                    System.out.println("Error Debug: ID Reservasi tidak ditemukan di reservasiMap!");
                    return; // Hentikan eksekusi
                }

                // Jika aman, baru simpan ke variabel primitif
                idReservasiDipilih = idReservasi;

                // 5. Ambil data lain dari map
                String tglCheckIn = reservasiTglCheckInMap.get(idReservasiDipilih);
                Double harga = reservasiHargaMap.get(idReservasiDipilih);
                Integer idKamar = reservasiIdKamarMap.get(idReservasiDipilih);

                // 6. PERIKSA NULL KEDUA: Apakah ID Kamar ditemukan?
                if (idKamar == null) {
                    System.out.println("Error Debug: ID Kamar tidak ditemukan di reservasiIdKamarMap!");
                    return; // Hentikan eksekusi
                }

                // Jika aman, simpan
                idKamarDipilih = idKamar;

                // 7. Tampilkan data ke text field (dengan pemeriksaan null)
                if (tglCheckIn != null) {
                    txtTglCheckIn.setText(tglCheckIn);
                } else {
                    txtTglCheckIn.setText("- (Data Tgl Kosong) -"); // Tanda jika data null
                }

                if (harga != null) {
                    txtHarga.setText(String.valueOf(harga));
                } else {
                    txtHarga.setText("0.0"); // Default jika data harga null
                }

                // 8. Aktifkan tombol Hitung
                btnHitung.setEnabled(true);

            } catch (Exception e) {
                // Tangkap error apapun yang mungkin terjadi
                JOptionPane.showMessageDialog(this, "Terjadi error saat memilih reservasi: " + e.getMessage());
                e.printStackTrace(); // Ini akan print error di console NetBeans
            }
        } else {
            // Jika user memilih "-- Pilih Reservasi --", panggil clearForm
            clearForm();
        }
    }//GEN-LAST:event_cmbReservasiActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
//        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnSimpanCheckOut;
    private javax.swing.JComboBox<String> cmbReservasi;
    private com.toedter.calendar.JDateChooser dateCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlahMalam;
    private javax.swing.JTextField txtTglCheckIn;
    private javax.swing.JTextField txtTotalTagihan;
    // End of variables declaration//GEN-END:variables
}
