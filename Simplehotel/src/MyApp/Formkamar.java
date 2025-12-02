package MyApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Formkamar extends javax.swing.JFrame {
    // Model untuk JTable
    private DefaultTableModel modelTabel;
    
    // Variabel untuk menyimpan ID kamar yang sedang dipilih (untuk Ubah/Hapus)
    private String idKamarDipilih = null;
    public Formkamar() {
        initComponents();
        // Atur judul jendela
        this.setTitle("Manajemen Data Kamar");
        
        // Panggil fungsi untuk inisialisasi tabel
        inisialisasiTabel();
        
        // Panggil fungsi untuk memuat data dari DB ke tabel
        loadDataKamar();
        
        // Panggil fungsi untuk membersihkan form
        clearForm();
    }
    private void inisialisasiTabel(){
        modelTabel = new DefaultTableModel();
        
        // Tambahkan kolom ke model
        modelTabel.addColumn("ID Kamar");
        modelTabel.addColumn("No. Kamar");
        modelTabel.addColumn("Tipe Kamar");
        modelTabel.addColumn("Harga/Malam");
        modelTabel.addColumn("Status");
        
        // Atur model ke JTable
        tblKamar.setModel(modelTabel);
        
        // Sembunyikan kolom ID Kamar (karena hanya untuk internal)
        tblKamar.getColumnModel().getColumn(0).setMinWidth(0);
        tblKamar.getColumnModel().getColumn(0).setMaxWidth(0);
        tblKamar.getColumnModel().getColumn(0).setWidth(0);
    }
    
    private void loadDataKamar(){
        // Hapus semua baris yang ada di model tabel
        modelTabel.setRowCount(0);
        
        Connection koneksi = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // 1. Dapatkan koneksi
            koneksi = Koneksi.getKoneksi();
            
            // 2. Buat statement
            stmt = koneksi.createStatement();
            
            // 3. Buat query SQL
            String sql = "SELECT * FROM tabel_kamar ORDER BY no_kamar ASC";
            
            // 4. Eksekusi query
            rs = stmt.executeQuery(sql);
            
            // 5. Looping data dari ResultSet dan tambahkan ke model
            while (rs.next()) {
                Object[] baris = new Object[5];
                baris[0] = rs.getInt("id_kamar"); // Ambil ID
                baris[1] = rs.getString("no_kamar");
                baris[2] = rs.getString("tipe_kamar");
                baris[3] = rs.getDouble("harga_per_malam");
                baris[4] = rs.getString("status");
                modelTabel.addRow(baris);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saat memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // 6. Tutup semua resource
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                // Biarkan koneksi terbuka jika Anda mau, tapi lebih baik ditutup jika tidak dipakai lagi
                // atau kelola di KoneksiDB jika ingin singleton. Untuk UAS, ini cukup.
            } catch (SQLException e) {
                System.out.println("Error saat menutup resource: " + e.getMessage());
            }
        }
    }
    
    private void clearForm() {
        // Kosongkan input field
        txtNoKamar.setText("");
        cmbTipeKamar.setSelectedIndex(0); // Kembali ke item pertama
        txtHarga.setText("");
        cmbStatus.setSelectedIndex(0); // Kembali ke item pertama
        
        // Setel ulang ID yang dipilih
        idKamarDipilih = null;
        
        // Atur status tombol
        btnSimpan.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        
        // Fokuskan kursor ke txtNoKamar
        txtNoKamar.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNoKamar = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        cmbTipeKamar = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKamar = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANAJEMEN DATA KAMAR");

        jLabel2.setText("No. Kamar");

        jLabel3.setText("Tipe Kamar");

        jLabel4.setText("Harga Per Malam");

        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        cmbTipeKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standard", "Deluxe", "Suite" }));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tersedia", "Terisi", "Pembersihan", "Perbaikan" }));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        tblKamar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKamarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKamar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbTipeKamar, 0, 213, Short.MAX_VALUE)
                                        .addComponent(txtNoKamar)
                                        .addComponent(txtHarga)))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbTipeKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnHapus)
                            .addComponent(btnBatal)
                            .addComponent(btnUbah)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // 1. Ambil data dari form
        String noKamar = txtNoKamar.getText();
        String tipeKamar = cmbTipeKamar.getSelectedItem().toString();
        String hargaStr = txtHarga.getText();
        String status = cmbStatus.getSelectedItem().toString();

        // 2. Validasi input sederhana
        if (noKamar.isEmpty() || hargaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Kamar dan Harga wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double harga;
        try {
            harga = Double.parseDouble(hargaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Proses simpan ke database
        Connection koneksi = null;
        PreparedStatement pstmt = null;

        try {
            koneksi = Koneksi.getKoneksi();
            
            // Query SQL untuk INSERT
            String sql = "INSERT INTO tabel_kamar (no_kamar, tipe_kamar, harga_per_malam, status) VALUES (?, ?, ?, ?)";
            
            pstmt = koneksi.prepareStatement(sql);
            
            // Set parameter PreparedStatement
            pstmt.setString(1, noKamar);
            pstmt.setString(2, tipeKamar);
            pstmt.setDouble(3, harga);
            pstmt.setString(4, status);
            
            // Eksekusi query
            int hasil = pstmt.executeUpdate();
            
            if (hasil > 0) {
                JOptionPane.showMessageDialog(this, "Data kamar berhasil disimpan.");
                loadDataKamar(); // Muat ulang data di tabel
                clearForm();     // Bersihkan form
            } else {
                JOptionPane.showMessageDialog(this, "Data kamar gagal disimpan.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Tangani jika ada error duplikat No. Kamar (UNIQUE constraint)
            if (e.getSQLState().equals("23000")) { // Kode SQLState untuk integrity constraint violation
                JOptionPane.showMessageDialog(this, "No. Kamar sudah ada, tidak boleh duplikat.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error SQL: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("Error saat menutup PreparedStatement: " + e.getMessage());
            }
        }                                         
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // 1. Validasi: Pastikan ada ID yang dipilih
        if (idKamarDipilih == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih data kamar dari tabel terlebih dahulu.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 2. Ambil data dari form
        String noKamar = txtNoKamar.getText();
        String tipeKamar = cmbTipeKamar.getSelectedItem().toString();
        String hargaStr = txtHarga.getText();
        String status = cmbStatus.getSelectedItem().toString();

        // 3. Validasi input
        if (noKamar.isEmpty() || hargaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Kamar dan Harga wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double harga;
        try {
            harga = Double.parseDouble(hargaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Proses Ubah ke database
        Connection koneksi = null;
        PreparedStatement pstmt = null;

        try {
            koneksi = Koneksi.getKoneksi();
            
            // Query SQL untuk UPDATE
            String sql = "UPDATE tabel_kamar SET no_kamar = ?, tipe_kamar = ?, harga_per_malam = ?, status = ? WHERE id_kamar = ?";
            
            pstmt = koneksi.prepareStatement(sql);
            
            // Set parameter
            pstmt.setString(1, noKamar);
            pstmt.setString(2, tipeKamar);
            pstmt.setDouble(3, harga);
            pstmt.setString(4, status);
            pstmt.setInt(5, Integer.parseInt(idKamarDipilih)); // ID Kamar yang di-WHERE
            
            // Eksekusi
            int hasil = pstmt.executeUpdate();
            
            if (hasil > 0) {
                JOptionPane.showMessageDialog(this, "Data kamar berhasil diubah.");
                loadDataKamar(); // Muat ulang tabel
                clearForm();     // Bersihkan form
            } else {
                JOptionPane.showMessageDialog(this, "Data kamar gagal diubah.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error SQL: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("Error saat menutup PreparedStatement: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // 1. Validasi: Pastikan ada ID yang dipilih
        if (idKamarDipilih == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih data kamar dari tabel terlebih dahulu.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 2. Konfirmasi penghapusan
        int pilihan = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin menghapus data ini?", 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        
        // Jika user memilih "Tidak" (NO)
        if (pilihan != JOptionPane.YES_OPTION) {
            return; // Batalkan proses hapus
        }

        // 3. Proses Hapus dari database
        Connection koneksi = null;
        PreparedStatement pstmt = null;

        try {
            koneksi = Koneksi.getKoneksi();
            
            // Query SQL untuk DELETE
            String sql = "DELETE FROM tabel_kamar WHERE id_kamar = ?";
            
            pstmt = koneksi.prepareStatement(sql);
            
            // Set parameter
            pstmt.setInt(1, Integer.parseInt(idKamarDipilih));
            
            // Eksekusi
            int hasil = pstmt.executeUpdate();
            
            if (hasil > 0) {
                JOptionPane.showMessageDialog(this, "Data kamar berhasil dihapus.");
                loadDataKamar(); // Muat ulang tabel
                clearForm();     // Bersihkan form
            } else {
                JOptionPane.showMessageDialog(this, "Data kamar gagal dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Tangani jika data tidak bisa dihapus karena relasi (misal, kamar sedang dipakai di tabel reservasi)
            if (e.getSQLState().equals("23000")) {
                JOptionPane.showMessageDialog(this, "Data kamar tidak bisa dihapus karena sudah digunakan di tabel reservasi.", "Error Relasi", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error SQL: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("Error saat menutup PreparedStatement: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clearForm();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void tblKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKamarMouseClicked
        // 1. Dapatkan nomor baris yang diklik
        int baris = tblKamar.getSelectedRow();
        
        // Pastikan ada baris yang terpilih
        if (baris == -1) {
            return;
        }

        // 2. Ambil data dari model tabel
        // Ingat, kolom 0 (id_kamar) kita sembunyikan, tapi datanya ada
        idKamarDipilih = modelTabel.getValueAt(baris, 0).toString();
        String noKamar = modelTabel.getValueAt(baris, 1).toString();
        String tipeKamar = modelTabel.getValueAt(baris, 2).toString();
        String harga = modelTabel.getValueAt(baris, 3).toString();
        String status = modelTabel.getValueAt(baris, 4).toString();

        // 3. Masukkan data ke form
        txtNoKamar.setText(noKamar);
        cmbTipeKamar.setSelectedItem(tipeKamar);
        txtHarga.setText(harga);
        cmbStatus.setSelectedItem(status);

        // 4. Atur status tombol
        btnSimpan.setEnabled(false); // Matikan tombol Simpan
        btnUbah.setEnabled(true);     // Aktifkan tombol Ubah
        btnHapus.setEnabled(true);    // Aktifkan tombol Hapus
    }//GEN-LAST:event_tblKamarMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formkamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formkamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formkamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formkamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formkamar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbTipeKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblKamar;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNoKamar;
    // End of variables declaration//GEN-END:variables
}
