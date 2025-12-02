/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package MyApp;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
/**
 *
 * @author MyBook Z Series
 */
public class PanelPemesanan extends javax.swing.JPanel {
private HashMap<String, Integer> tamuMap = new HashMap<>();
    private HashMap<String, Integer> kamarMap = new HashMap<>();
    private HashMap<Integer, Double> kamarHargaMap = new HashMap<>();
    private HashMap<Integer, String> kamarTipeMap = new HashMap<>();

    public PanelPemesanan() {
        initComponents();
        
        // Load data tamu saat panel dibuka
        loadDataTamu();
        
        // Set tanggal default hari ini dan besok
        dateCheckIn.setDate(new Date());
        Date besok = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
        dateCheckOut.setDate(besok);
        
        // Matikan combo box kamar sebelum user klik "Cek Ketersediaan"
        cmbKamar.setEnabled(false);
        btnSimpanBooking.setEnabled(false);
    }

    private void loadDataTamu() {
        // ... (Sama persis dengan kodingan di PanelCheckIn) ...
        // Copas saja kodingan loadDataTamu dari PanelCheckIn ke sini
        // Untuk menghemat tempat, saya singkat di sini.
        try {
            Connection k = Koneksi.getKoneksi();
            Statement s = k.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM tabel_tamu ORDER BY nama_tamu ASC");
            cmbTamu.removeAllItems();
            tamuMap.clear();
            cmbTamu.addItem("-- Pilih Tamu --");
            while(r.next()){
                cmbTamu.addItem(r.getString("nama_tamu"));
                tamuMap.put(r.getString("nama_tamu"), r.getInt("id_tamu"));
            }
        } catch (Exception e) {}
    }

    // === LOGIKA INTI: MENCARI KAMAR KOSONG BERDASARKAN TANGGAL ===
    private void loadKamarKosong() {
        Date dIn = dateCheckIn.getDate();
        Date dOut = dateCheckOut.getDate();

        if (dIn == null || dOut == null) {
            JOptionPane.showMessageDialog(this, "Harap pilih tanggal Check-In dan Check-Out!");
            return;
        }
        
        if (dOut.before(dIn) || dOut.equals(dIn)) {
            JOptionPane.showMessageDialog(this, "Tanggal Check-Out harus setelah Check-In!");
            return;
        }

        Connection koneksi = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            koneksi = Koneksi.getKoneksi();

            // QUERY SAKTI ANTI-BENTROK
            // Artinya: Ambil kamar yg TIDAK ADA di daftar reservasi (Booking/Aktif) 
            // yang tanggalnya tumpang tindih dengan request kita.
            String sql = "SELECT * FROM tabel_kamar WHERE id_kamar NOT IN (" +
                         "    SELECT id_kamar FROM tabel_reservasi " +
                         "    WHERE status_reservasi IN ('Booking', 'Aktif') " +
                         "    AND (" +
                         "       (tgl_checkin < ? AND tgl_checkout > ?) " + 
                         "    )" +
                         ") AND status != 'Perbaikan' " + // Pastikan kamar tidak sedang rusak
                         "ORDER BY no_kamar ASC";

            pstmt = koneksi.prepareStatement(sql);
            
            // Parameter 1: Tanggal Check-Out Request (java.sql.Date)
            pstmt.setDate(1, new java.sql.Date(dOut.getTime()));
            // Parameter 2: Tanggal Check-In Request (java.sql.Date)
            pstmt.setDate(2, new java.sql.Date(dIn.getTime()));

            rs = pstmt.executeQuery();

            // Reset Combo Box
            cmbKamar.removeAllItems();
            kamarMap.clear();
            kamarHargaMap.clear();
            kamarTipeMap.clear();
            cmbKamar.addItem("-- Pilih Kamar Tersedia --");

            boolean adaKamar = false;
            while (rs.next()) {
                adaKamar = true;
                int id = rs.getInt("id_kamar");
                String no = rs.getString("no_kamar");
                String tipe = rs.getString("tipe_kamar");
                double harga = rs.getDouble("harga_per_malam");

                cmbKamar.addItem(no);
                kamarMap.put(no, id);
                kamarHargaMap.put(id, harga);
                kamarTipeMap.put(id, tipe);
            }
            
            if (adaKamar) {
                cmbKamar.setEnabled(true);
                btnSimpanBooking.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Data kamar tersedia berhasil dimuat.");
            } else {
                cmbKamar.setEnabled(false);
                btnSimpanBooking.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Maaf, tidak ada kamar tersedia pada tanggal tersebut.", "Penuh", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error SQL: " + e.getMessage());
        } finally {
             try { if (rs != null) rs.close(); if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateCheckIn = new com.toedter.calendar.JDateChooser();
        dateCheckOut = new com.toedter.calendar.JDateChooser();
        btnCekKetersediaan = new javax.swing.JButton();
        cmbTamu = new javax.swing.JComboBox<>();
        cmbKamar = new javax.swing.JComboBox<>();
        txtHarga = new javax.swing.JTextField();
        txtTipe = new javax.swing.JTextField();
        btnSimpanBooking = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulir Check Out Tamu");

        jLabel3.setText("Pilih Reservasi (Tamu - Kamar)");

        jLabel5.setText("Tgl. CheckIn");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Formulir Pemesanan Kamar");

        btnCekKetersediaan.setText("Cek Ketersediaan");
        btnCekKetersediaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekKetersediaanActionPerformed(evt);
            }
        });

        cmbTamu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKamarActionPerformed(evt);
            }
        });

        btnSimpanBooking.setText("Simpan Booking");
        btnSimpanBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanBookingActionPerformed(evt);
            }
        });

        jLabel6.setText("Tgl. CheckIn");

        jLabel7.setText("Tgl. CheckOut");

        jLabel8.setText("Pilih Tamu");

        jLabel9.setText("Pilih Kamar");

        jLabel10.setText("Harga");

        jLabel11.setText("Tipe");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpanBooking)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnCekKetersediaan)
                                .addComponent(dateCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                .addComponent(cmbTamu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbKamar, 0, 320, Short.MAX_VALUE)
                                .addComponent(txtHarga)
                                .addComponent(txtTipe)))
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCekKetersediaan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTamu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpanBooking)
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCekKetersediaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekKetersediaanActionPerformed
        loadKamarKosong();
    }//GEN-LAST:event_btnCekKetersediaanActionPerformed

    private void cmbKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKamarActionPerformed
        if (cmbKamar.getItemCount() > 0 && cmbKamar.getSelectedIndex() > 0) {
            String no = cmbKamar.getSelectedItem().toString();
            int id = kamarMap.get(no);
            txtTipe.setText(kamarTipeMap.get(id));
            txtHarga.setText(String.valueOf(kamarHargaMap.get(id)));
        } else {
            txtTipe.setText("");
            txtHarga.setText("");
        }
    }//GEN-LAST:event_cmbKamarActionPerformed

    private void btnSimpanBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanBookingActionPerformed
        if (cmbTamu.getSelectedIndex() <= 0 || cmbKamar.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Lengkapi data tamu dan kamar!");
            return;
        }
        
        try {
            Connection k = Koneksi.getKoneksi();
            // QUERY: INSERT dengan Status 'Booking'
            // Kita TIDAK update tabel_kamar statusnya. Biarkan tetap 'Tersedia'.
            String sql = "INSERT INTO tabel_reservasi (id_tamu, id_kamar, tgl_checkin, tgl_checkout, status_reservasi) VALUES (?, ?, ?, ?, 'Booking')";
            PreparedStatement p = k.prepareStatement(sql);
            
            String nama = cmbTamu.getSelectedItem().toString();
            String noKamar = cmbKamar.getSelectedItem().toString();
            
            p.setInt(1, tamuMap.get(nama));
            p.setInt(2, kamarMap.get(noKamar));
            p.setDate(3, new java.sql.Date(dateCheckIn.getDate().getTime()));
            p.setDate(4, new java.sql.Date(dateCheckOut.getDate().getTime()));
            
            int hasil = p.executeUpdate();
            if (hasil > 0) {
                JOptionPane.showMessageDialog(this, "Booking Berhasil Disimpan!");
                // Reset form
                cmbKamar.removeAllItems();
                cmbKamar.setEnabled(false);
                btnSimpanBooking.setEnabled(false);
                txtTipe.setText("");
                txtHarga.setText("");
                cmbTamu.setSelectedIndex(0);
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanBookingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCekKetersediaan;
    private javax.swing.JButton btnSimpanBooking;
    private javax.swing.JComboBox<String> cmbKamar;
    private javax.swing.JComboBox<String> cmbTamu;
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private com.toedter.calendar.JDateChooser dateCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtTipe;
    // End of variables declaration//GEN-END:variables
}
