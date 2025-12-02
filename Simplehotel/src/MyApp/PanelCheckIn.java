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
import java.text.SimpleDateFormat; // Untuk format tanggal
import java.util.Date; // Untuk tanggal hari ini
import java.util.HashMap; // Untuk menyimpan ID di JComboBox
import javax.swing.JOptionPane;
/**
 *
 * @author MyBook Z Series
 */
public class PanelCheckIn extends javax.swing.JPanel {
 // HashMap untuk menyimpan relasi antara Nama (String) di JComboBox dan ID (Integer) di database
    private HashMap<String, Integer> tamuMap;
    private HashMap<String, Integer> kamarMap;
    
    // HashMap untuk menyimpan detail kamar (Tipe dan Harga)
    private HashMap<Integer, String> kamarTipeMap;
    private HashMap<Integer, Double> kamarHargaMap;
    /**
     * Creates new form FormCheckIn
     */
    public PanelCheckIn() {
        initComponents();
        // Inisialisasi HashMap
        tamuMap = new HashMap<>();
        kamarMap = new HashMap<>();
        kamarTipeMap = new HashMap<>();
        kamarHargaMap = new HashMap<>();
        
        // Atur judul
//        this.setTitle("Formulir Check-In");
        
        // Panggil fungsi untuk mengisi data ke combo box
        loadDataTamu();
        loadDataKamar();
        
        // Atur tanggal hari ini ke JDateChooser
        dateCheckIn.setDate(new Date());
        
        // Atur agar text field tidak bisa di-edit
        txtTipeKamar.setEditable(false);
        txtHarga.setEditable(false);
    }

    // Fungsi untuk memuat data tamu ke cmbTamu
    private void loadDataTamu() {
        Connection koneksi = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            koneksi = Koneksi.getKoneksi();
            stmt = koneksi.createStatement();
            
            String sql = "SELECT id_tamu, nama_tamu FROM tabel_tamu ORDER BY nama_tamu ASC";
            rs = stmt.executeQuery(sql);

            // Bersihkan combo box dan map
            cmbTamu.removeAllItems();
            tamuMap.clear();
            
            cmbTamu.addItem("-- Pilih Tamu --"); // Item default

            while (rs.next()) {
                int idTamu = rs.getInt("id_tamu");
                String namaTamu = rs.getString("nama_tamu");
                
                // Tambahkan nama ke JComboBox
                cmbTamu.addItem(namaTamu);
                // Simpan relasi Nama -> ID di HashMap
                tamuMap.put(namaTamu, idTamu);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error memuat data tamu: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }
    
    // Fungsi untuk memuat data kamar yang TERSEDIA ke cmbKamar
    private void loadDataKamar() {
        Connection koneksi = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            koneksi = Koneksi.getKoneksi();
            stmt = koneksi.createStatement();
            
            // Query HANYA kamar yang statusnya 'Tersedia'
            String sql = "SELECT id_kamar, no_kamar, tipe_kamar, harga_per_malam FROM tabel_kamar WHERE status = 'Tersedia' ORDER BY no_kamar ASC";
            rs = stmt.executeQuery(sql);

            // Bersihkan combo box dan map
            cmbKamar.removeAllItems();
            kamarMap.clear();
            kamarTipeMap.clear();
            kamarHargaMap.clear();
            
            cmbKamar.addItem("-- Pilih Kamar --"); // Item default

            while (rs.next()) {
                int idKamar = rs.getInt("id_kamar");
                String noKamar = rs.getString("no_kamar");
                String tipeKamar = rs.getString("tipe_kamar");
                double harga = rs.getDouble("harga_per_malam");
                
                // Tambahkan No Kamar ke JComboBox
                cmbKamar.addItem(noKamar);
                // Simpan relasi No Kamar -> ID di HashMap
                kamarMap.put(noKamar, idKamar);
                // Simpan detail kamar di HashMap lain
                kamarTipeMap.put(idKamar, tipeKamar);
                kamarHargaMap.put(idKamar, harga);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error memuat data kamar: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
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
        jLabel6 = new javax.swing.JLabel();
        dateCheckIn = new com.toedter.calendar.JDateChooser();
        txtHarga = new javax.swing.JTextField();
        btnSimpanCheckIn = new javax.swing.JButton();
        cmbTamu = new javax.swing.JComboBox<>();
        btnBatal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbKamar = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTipeKamar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulir Check Out Tamu");

        jLabel6.setText("Harga/Malam");

        txtHarga.setEditable(false);

        btnSimpanCheckIn.setText("Simpan Check in");
        btnSimpanCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanCheckInActionPerformed(evt);
            }
        });

        cmbTamu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel4.setText("Pilih Kamar (Hanya yang tersedia)");

        jLabel3.setText("Tanggal Check-In");

        cmbKamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKamarActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipe Kamar");

        jLabel2.setText("Pilih Tamu");

        txtTipeKamar.setEditable(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Formulir Check In Tamu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpanCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbTamu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbKamar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTipeKamar)
                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbTamu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTipeKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanCheckIn)
                    .addComponent(btnBatal))
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanCheckInActionPerformed
        // 1. Validasi Input
        if (dateCheckIn.getDate() == null || cmbTamu.getSelectedIndex() <= 0 || cmbKamar.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Semua data (Tanggal, Tamu, dan Kamar) wajib dipilih!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Ambil data dari Form

        // Ambil ID Tamu
        String namaTamu = cmbTamu.getSelectedItem().toString();
        int idTamu = tamuMap.get(namaTamu);

        // Ambil ID Kamar
        String noKamar = cmbKamar.getSelectedItem().toString();
        int idKamar = kamarMap.get(noKamar);

        // Ambil Tanggal Check-In dan format ke SQL Date
        java.util.Date tglCheckInUtil = dateCheckIn.getDate();
        java.sql.Date tglCheckInSql = new java.sql.Date(tglCheckInUtil.getTime());

        // 3. Proses ke Database (Dua Query)
        Connection koneksi = null;
        PreparedStatement pstmt1 = null; // Untuk INSERT ke reservasi
        PreparedStatement pstmt2 = null; // Untuk UPDATE status kamar

        try {
            koneksi = Koneksi.getKoneksi();
            // PENTING: Matikan auto-commit untuk 'Transaction'
            koneksi.setAutoCommit(false);

            // === QUERY 1: INSERT KE TABEL RESERVASI ===
            String sql1 = "INSERT INTO tabel_reservasi (id_tamu, id_kamar, tgl_checkin, status_reservasi) VALUES (?, ?, ?, ?)";
            pstmt1 = koneksi.prepareStatement(sql1);
            pstmt1.setInt(1, idTamu);
            pstmt1.setInt(2, idKamar);
            pstmt1.setDate(3, tglCheckInSql);
            pstmt1.setString(4, "Aktif"); // Status reservasi baru adalah 'Aktif'

            int hasil1 = pstmt1.executeUpdate();

            // === QUERY 2: UPDATE STATUS KAMAR ===
            String sql2 = "UPDATE tabel_kamar SET status = 'Terisi' WHERE id_kamar = ?";
            pstmt2 = koneksi.prepareStatement(sql2);
            pstmt2.setInt(1, idKamar);

            int hasil2 = pstmt2.executeUpdate();

            // 4. Selesaikan Transaksi (Commit)
            if (hasil1 > 0 && hasil2 > 0) {
                koneksi.commit(); // Simpan kedua perubahan jika berhasil
                JOptionPane.showMessageDialog(this, "Check-In berhasil disimpan.");

                // Refresh data di form
                loadDataKamar(); // Kamar yang dipesan akan hilang dari daftar
                txtTipeKamar.setText("");
                txtHarga.setText("");
                cmbTamu.setSelectedIndex(0);

            } else {
                koneksi.rollback(); // Batalkan jika salah satu gagal
                JOptionPane.showMessageDialog(this, "Check-In Gagal. Ada kesalahan pada database.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            // Jika terjadi error, batalkan semua perubahan
            try {
                if (koneksi != null) koneksi.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback gagal: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Terjadi Error SQL: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } finally {
            // Kembalikan ke mode auto-commit
            try {
                if (koneksi != null) koneksi.setAutoCommit(true);
            } catch (SQLException e) {}

            // Tutup semua PreparedStatement
            try { if (pstmt1 != null) pstmt1.close(); } catch (Exception e) {}
            try { if (pstmt2 != null) pstmt2.close(); } catch (Exception e) {}
            // Koneksi jangan ditutup di sini, biarkan dikelola oleh KoneksiDB
        }
    }//GEN-LAST:event_btnSimpanCheckInActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
//        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void cmbKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKamarActionPerformed
        // Cek apakah item yang dipilih valid (bukan "-- Pilih Kamar --")
        if (cmbKamar.getSelectedIndex() > 0) {
            // Ambil No Kamar yang dipilih
            String noKamar = cmbKamar.getSelectedItem().toString();

            // Ambil ID Kamar dari HashMap
            int idKamar = kamarMap.get(noKamar);

            // Ambil Tipe dan Harga dari HashMap detail
            String tipe = kamarTipeMap.get(idKamar);
            Double harga = kamarHargaMap.get(idKamar);

            // Tampilkan di text field
            txtTipeKamar.setText(tipe);
            txtHarga.setText(String.valueOf(harga)); // Konversi Double ke String
        } else {
            // Jika memilih "-- Pilih Kamar --", kosongkan text field
            txtTipeKamar.setText("");
            txtHarga.setText("");
        }
    }//GEN-LAST:event_cmbKamarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpanCheckIn;
    private javax.swing.JComboBox<String> cmbKamar;
    private javax.swing.JComboBox<String> cmbTamu;
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtTipeKamar;
    // End of variables declaration//GEN-END:variables
}
