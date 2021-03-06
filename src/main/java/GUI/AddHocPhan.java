/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import constants.Constants;
import hocphan.HocPhan;
import hocphan.HocPhanDAO;
import javax.swing.JOptionPane;
import monhoc.MonHoc;

/**
 *
 * @author Admin
 */
public class AddHocPhan extends javax.swing.JFrame {

    /**
     * Creates new form AddHocPhan
     */
    private ManagerDashboard md;
    public AddHocPhan(ManagerDashboard md) {
        this.md = md;
        initComponents();
    }

    private AddHocPhan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String[] ngayTrongTuan(){
        String[] result ={
            Constants.MON,
            Constants.TUE,
            Constants.WED,
            Constants.THU,
            Constants.FRI,
            Constants.SAT,
            Constants.SUN
        };
        return result;
    }
    public String[] caHocStrings(){
        String[] result ={
            Constants.CA_1,
            Constants.CA_2,
            Constants.CA_3,
            Constants.CA_4,
        };
        return result;        
    }
    public String[] dsTenMonHoc(){
        String[] result = new String[this.md.getDsMonHoc().size()];
        for (int i=0; i<this.md.getDsMonHoc().size();i++){
            result[i] = this.md.getDsMonHoc().get(i).getTenMonHoc();
        }
        return result;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        monCombo = new javax.swing.JComboBox<>();
        gvText = new javax.swing.JTextField();
        slotSpinner = new javax.swing.JSpinner();
        ngayCombo = new javax.swing.JComboBox<>();
        caCombo = new javax.swing.JComboBox<>();
        phongText = new javax.swing.JTextField();
        huyBtn = new javax.swing.JButton();
        xacNhanBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Th??m h???c ph???n");

        jLabel1.setText("M??n h???c:");

        jLabel2.setText("GV l?? thuy???t:");

        jLabel3.setText("Slot t???i ??a:");

        jLabel4.setText("Ng??y h???c:");

        jLabel5.setText("Ca h???c:");

        jLabel6.setText("Ph??ng h???c:");

        monCombo.setModel(new javax.swing.DefaultComboBoxModel<>(dsTenMonHoc()));

        slotSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 250, 1));

        ngayCombo.setModel(new javax.swing.DefaultComboBoxModel<>(ngayTrongTuan()));

        caCombo.setModel(new javax.swing.DefaultComboBoxModel<>(caHocStrings()));
        caCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caComboActionPerformed(evt);
            }
        });

        huyBtn.setText("H???y b???");
        huyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huyBtnActionPerformed(evt);
            }
        });

        xacNhanBtn.setText("X??c nh???n");
        xacNhanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xacNhanBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(monCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gvText)
                    .addComponent(slotSpinner)
                    .addComponent(ngayCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(caCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phongText)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(huyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(xacNhanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(monCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(gvText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(slotSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ngayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(caCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(phongText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(huyBtn)
                    .addComponent(xacNhanBtn))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void huyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huyBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_huyBtnActionPerformed

    private void xacNhanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xacNhanBtnActionPerformed
        // TODO add your handling code here:
        int indexMon = monCombo.getSelectedIndex();
        MonHoc monHoc = md.getDsMonHoc().get(indexMon);
        String gvLyThuyet = gvText.getText();
        Integer slot = (Integer)slotSpinner.getValue();
        String ngayHoc = (String)ngayCombo.getSelectedItem();
        Integer caHoc = caCombo.getSelectedIndex() + 1;
        String tenPhong = phongText.getText();
        
        HocPhan hocPhan = new HocPhan(caHoc, slot, gvLyThuyet, tenPhong, ngayHoc);
        boolean result = HocPhanDAO.taoHocPhan(hocPhan, monHoc);
        if (result){
            JOptionPane.showConfirmDialog(null,"T???o h???c ph???n th??nh c??ng",Constants.SUCCESS,JOptionPane.DEFAULT_OPTION);
            md.updateAllTable("");
        }else{
            JOptionPane.showConfirmDialog(null,Constants.MISSING_PARAMS,Constants.FAIL,JOptionPane.DEFAULT_OPTION);  
        }
        this.dispose();
    }//GEN-LAST:event_xacNhanBtnActionPerformed

    private void caComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caComboActionPerformed

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
            java.util.logging.Logger.getLogger(AddHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddHocPhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> caCombo;
    private javax.swing.JTextField gvText;
    private javax.swing.JButton huyBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox<String> monCombo;
    private javax.swing.JComboBox<String> ngayCombo;
    private javax.swing.JTextField phongText;
    private javax.swing.JSpinner slotSpinner;
    private javax.swing.JButton xacNhanBtn;
    // End of variables declaration//GEN-END:variables
}
