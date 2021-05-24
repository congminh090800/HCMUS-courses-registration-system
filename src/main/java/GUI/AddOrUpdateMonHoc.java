/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import constants.Constants;
import javax.swing.JOptionPane;
import monhoc.MonHoc;
import monhoc.MonHocDAO;
import utils.DateTimeUtil;

/**
 *
 * @author Admin
 */
public class AddOrUpdateMonHoc extends javax.swing.JFrame {

    /**
     * Creates new form AddOrUpdateMonHoc
     */
    private int mode;
    private MonHoc monHoc;
    private ManagerDashboard md;
    public AddOrUpdateMonHoc(int mode, String maMonHoc, ManagerDashboard md) {
        this.mode = mode;
        if (!maMonHoc.equals(""))
            this.monHoc = MonHocDAO.timMonHoc(maMonHoc);
        this.md = md;
        initComponents();
        if (mode == Constants.UPDATE_MODE) setAvailableData();
    }

    private AddOrUpdateMonHoc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        maMonHocText = new javax.swing.JTextField();
        tenMonHocText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        soTinChiSpinner = new javax.swing.JSpinner();
        xacNhanBtn = new javax.swing.JButton();
        huyBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thêm/Cập nhật môn học");
        setResizable(false);
        setSize(new java.awt.Dimension(344, 238));

        jLabel3.setText("Mã môn học:");

        jLabel4.setText("Tên môn học:");

        jLabel5.setText("Số tín chỉ:");

        soTinChiSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        soTinChiSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        xacNhanBtn.setText("Xác nhận");
        xacNhanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xacNhanBtnActionPerformed(evt);
            }
        });

        huyBtn.setText("Hủy bỏ");
        huyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huyBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(huyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xacNhanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addComponent(maMonHocText)
                    .addComponent(tenMonHocText)
                    .addComponent(soTinChiSpinner))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maMonHocText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenMonHocText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soTinChiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(huyBtn)
                    .addComponent(xacNhanBtn))
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setAvailableData(){
        maMonHocText.setText(monHoc.getMaMonHoc());
        tenMonHocText.setText(monHoc.getTenMonHoc());
        soTinChiSpinner.setValue(monHoc.getSoTinChi());
    }
    
    private void xacNhanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xacNhanBtnActionPerformed
        // TODO add your handling code here:
        boolean result = false;
        if (mode == Constants.ADD_MODE) monHoc = new MonHoc();
        monHoc.setMaMonHoc(maMonHocText.getText());
        monHoc.setTenMonHoc(tenMonHocText.getText());
        monHoc.setSoTinChi((Integer)soTinChiSpinner.getValue());
        if (mode == Constants.UPDATE_MODE){
            result = MonHocDAO.capNhatThongTin(monHoc);
        }else{
            result = MonHocDAO.taoMonHoc(monHoc);
        }
        if (result) {
            if (mode == Constants.UPDATE_MODE)
                JOptionPane.showConfirmDialog(null,"Cập nhật thành công",Constants.SUCCESS,JOptionPane.DEFAULT_OPTION);
            else JOptionPane.showConfirmDialog(null,"Tạo thành công",Constants.SUCCESS,JOptionPane.DEFAULT_OPTION);
            md.updateAllTable("");
        }else {
            JOptionPane.showConfirmDialog(null,Constants.MISSING_PARAMS,Constants.FAIL,JOptionPane.DEFAULT_OPTION);            
        }       
        this.dispose();        
    }//GEN-LAST:event_xacNhanBtnActionPerformed

    private void huyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huyBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_huyBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AddOrUpdateMonHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddOrUpdateMonHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddOrUpdateMonHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddOrUpdateMonHoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddOrUpdateMonHoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton huyBtn;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField maMonHocText;
    private javax.swing.JSpinner soTinChiSpinner;
    private javax.swing.JTextField tenMonHocText;
    private javax.swing.JButton xacNhanBtn;
    // End of variables declaration//GEN-END:variables
}
