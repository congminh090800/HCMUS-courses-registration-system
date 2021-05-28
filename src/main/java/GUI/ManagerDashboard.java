/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import constants.Constants;
import dkhp.DKHP;
import dkhp.DKHPDAO;
import dkhp.SinhVienHocPhanDAO;
import hocki.HocKi;
import hocki.HocKiDAO;
import hocki.HocKiHienTai;
import hocki.HocKiHienTaiDAO;
import hocphan.HocPhan;
import hocphan.HocPhanDAO;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lop.Lop;
import lop.LopDAO;
import monhoc.MonHoc;
import monhoc.MonHocDAO;
import taikhoan.TaiKhoan;
import taikhoan.TaiKhoanDAO;
import utils.DateTimeUtil;
import vaitro.GiaoVu;
import vaitro.GiaoVuDAO;
import vaitro.SinhVien;
import vaitro.SinhVienDAO;

/**
 *
 * @author Admin
 */
public class ManagerDashboard extends javax.swing.JFrame {

    /**
     * Creates new form ManagerDashboard
     */
    private GiaoVu giaoVu;
    private List<GiaoVu> dsGiaoVu = null;
    private List<MonHoc> dsMonHoc = null;
    private List<HocKi> dsHocKi = null;
    private List<Lop> dsLop = null;
    private List<DKHP> dsKiDKHP = null;
    private List<HocPhan> dsHocPhan = null;

    public List<Lop> getDsLop() {
        return dsLop;
    }

    public List<HocPhan> getDsHocPhan() {
        return dsHocPhan;
    }

    public List<MonHoc> getDsMonHoc() {
        return dsMonHoc;
    }
    
    private List<SinhVien> dsSinhVien = new ArrayList<>();
            
    public ManagerDashboard(TaiKhoan tk) {
        this.giaoVu = GiaoVuDAO.timGiaoVuTK(tk.getIdTk());
        loadData();
        initComponents();
    }

    public ManagerDashboard() {
        this.giaoVu = GiaoVuDAO.timGiaoVu(1);
        loadData();
        initComponents();
    }
    
    public final void loadData(){
        dsGiaoVu = GiaoVuDAO.layDanhSach("");
        dsMonHoc = MonHocDAO.layDanhSach("");
        dsHocKi = HocKiDAO.dsHocKi();
        dsLop = LopDAO.dsLop();
        if (dsLop.size()>0){
            dsSinhVien = LopDAO.timSvTrongLop(dsLop.get(0),"");
        }
        dsKiDKHP = DKHPDAO.layDanhSach();
        dsHocPhan = HocPhanDAO.layDanhSach("");
    }
    public String[][] mappingDsGiaoVu(List<GiaoVu> dsGiaoVu){
        String[][] result = new String[dsGiaoVu.size()][Constants.giaoVuHeader.length];
        for (int i=0;i<dsGiaoVu.size();i++){
            GiaoVu gv = dsGiaoVu.get(i);
            String[] giaoVuHienTai={gv.getMaGv(),gv.getHoTen(),gv.getDiaChi(),gv.getSdt(),
                                    gv.getGioiTinh(),DateTimeUtil.convertToStringViaDate(gv.getNgaySinh()),
                                    DateTimeUtil.convertToStringViaTimestamp(gv.getTaiKhoan().getNgayTao())};
            result[i]=giaoVuHienTai;
        }
        return result;
    }
    public String[][] mappingDsMonHoc(List<MonHoc> dsMonHoc){
        String[][] result = new String[dsMonHoc.size()][Constants.monHocHeader.length];
        for (int i=0;i<dsMonHoc.size();i++){
            MonHoc t = dsMonHoc.get(i);
            String[] mh={t.getMaMonHoc(),t.getTenMonHoc(),String.valueOf(t.getSoTinChi())};
            result[i]=mh;
        }
        return result;
    }
    public String[][] mappingDsHocKi (List<HocKi> dsHocKi){
        String[][] result = new String[dsHocKi.size()][Constants.hocKiHeader.length];
        for (int i=0;i<dsHocKi.size();i++){
            HocKi t = dsHocKi.get(i);
            String[] hocKi={t.getTenHocKi(),String.valueOf(t.getNamHoc()),
                                    DateTimeUtil.convertToStringViaDate(t.getNgayBatDau()),
                                    DateTimeUtil.convertToStringViaDate(t.getNgayKetThuc())};
            result[i]=hocKi;
        }
        return result;        
    }
    public String[][] mappingDsLop (List<Lop> dsLop){
        String[][] result = new String[dsLop.size()][Constants.lopHeader.length];
        for (int i=0;i<dsLop.size();i++){
            Lop t = dsLop.get(i);
            Integer soLuong = LopDAO.demSoLuongSV(t.getIdLop(), null);
            Integer soLuongNam = LopDAO.demSoLuongSV(t.getIdLop(), "nam");
            Integer soLuongNu = soLuong - soLuongNam;
            String[] l={t.getTenLop(),String.valueOf(soLuong),String.valueOf(soLuongNam),String.valueOf(soLuongNu)};
            result[i]=l;
        }
        return result;        
    }
    public String[] layDsTenLop (List<Lop> dsLop){
        String[] result = new String[dsLop.size()];
        for (int i=0;i<dsLop.size();i++){
            Lop t = dsLop.get(i);
            result[i] = t.getTenLop();
        }
        return result;        
    }
    public String[][] mappingDsSinhVien(List<SinhVien> dsSinhVien){
        String[][] result = new String[dsSinhVien.size()][Constants.sinhVienHeader.length];
        for (int i=0;i<dsSinhVien.size();i++){
            SinhVien sv = dsSinhVien.get(i);
            String[] s={sv.getMssv(),sv.getHoTen(),sv.getDiaChi(),sv.getSdt(),
                                    sv.getGioiTinh(),DateTimeUtil.convertToStringViaDate(sv.getNgaySinh()),
                                    DateTimeUtil.convertToStringViaTimestamp(sv.getTaiKhoan().getNgayTao()),
                                    sv.getLop().getTenLop()};
            result[i]=s;
        }
        return result;
    }
    public String[][] mappingDsKiDKHP(List<DKHP> dsKiDKHP){
        String[][] result = new String[dsKiDKHP.size()][Constants.kiDKHPHeader.length];
        for (int i=0;i<dsKiDKHP.size();i++){
            DKHP dk = dsKiDKHP.get(i);
            String[] d={DateTimeUtil.convertToStringViaDate(dk.getNgayBatDau()),
                        DateTimeUtil.convertToStringViaDate(dk.getNgayKetThuc()),
                        dk.getHocKi().getTenHocKi(),
                        String.valueOf(dk.getHocKi().getNamHoc())};
            result[i]=d;
        }
        return result;        
    }
    public String[][] mappingDsHocPhan(List<HocPhan> dsHocPhan){
        String[][] result = new String[dsHocPhan.size()][Constants.hocPhanHeader.length];
        for (int i=0;i<dsHocPhan.size();i++){
            HocPhan hp = dsHocPhan.get(i);
            String[] d={
                hp.getMaHocPhan(), hp.getMonHoc().getTenMonHoc(),String.valueOf(hp.getMonHoc().getSoTinChi()),
                hp.getGvLyThuyet(),String.valueOf(hp.getSoLuongToiDa()),hp.getNgayHoc(),
                caHocIntToString(hp.getCaHoc()-1),hp.getTenPhongHoc()                   
            };
            result[i]=d;
        }
        return result;    
    }
    public String caHocIntToString(int index){
        return Constants.caHocStrings[index];
    }
    public void updateAllTable(String keyword){
        updateTable(keyword);
        updateMonHocTable(keyword);
        updateHocKiTable();
        updateLopTable();
        updateSinhVienTable(keyword);
        updateKiDKHPTable();
        updateHocPhanTable(keyword);
        lopCombo.setModel(new DefaultComboBoxModel<String>(layDsTenLop(dsLop)));
    }
    public void updateTable(String keyword){
        DefaultTableModel tableModel = (DefaultTableModel) giaoVuTable.getModel();
        dsGiaoVu = GiaoVuDAO.layDanhSach(keyword);
        tableModel.setDataVector(mappingDsGiaoVu(dsGiaoVu), Constants.giaoVuHeader);
        tableModel.fireTableDataChanged();
        soKetQuaLabel.setText(dsGiaoVu.size() + " kết quả");
    }
    public void updateMonHocTable(String keyword){
        DefaultTableModel tableModel = (DefaultTableModel) monHocTable.getModel();
        dsMonHoc = MonHocDAO.layDanhSach(keyword);
        tableModel.setDataVector(mappingDsMonHoc(dsMonHoc), Constants.monHocHeader);
        tableModel.fireTableDataChanged();      
        soKetQuaMhLabel.setText(dsMonHoc.size() + " kết quả");
    }
    public void updateHocKiTable(){
        DefaultTableModel tableModel = (DefaultTableModel) hocKiTable.getModel();
        dsHocKi = HocKiDAO.dsHocKi();
        tableModel.setDataVector(mappingDsHocKi(dsHocKi), Constants.hocKiHeader);
        tableModel.fireTableDataChanged(); 
    }
    public void updateLopTable(){
        DefaultTableModel tableModel = (DefaultTableModel) lopTable.getModel();
        dsLop = LopDAO.dsLop();
        tableModel.setDataVector(mappingDsLop(dsLop), Constants.lopHeader);
        tableModel.fireTableDataChanged();        
    }
    public void updateSinhVienTable(String keyword){
        DefaultTableModel tableModel = (DefaultTableModel) sinhVienTable.getModel();
        int index = lopCombo.getSelectedIndex();
        Lop l = dsLop.get(index);
        dsSinhVien = LopDAO.timSvTrongLop(l, keyword);
        tableModel.setDataVector(mappingDsSinhVien(dsSinhVien), Constants.sinhVienHeader);
        tableModel.fireTableDataChanged();

        soSinhVienLabel.setText(dsSinhVien.size() + " kết quả");
    }
    public void updateKiDKHPTable(){
        DefaultTableModel tableModel = (DefaultTableModel) kiDKHPTable.getModel();
        dsKiDKHP = DKHPDAO.layDanhSach();
        tableModel.setDataVector(mappingDsKiDKHP(dsKiDKHP), Constants.kiDKHPHeader);
        tableModel.fireTableDataChanged();         
    }
    public void updateHocPhanTable(String keyword){
        DefaultTableModel tableModel = (DefaultTableModel) hocPhanTable.getModel();
        dsHocPhan = HocPhanDAO.layDanhSach(keyword);
        tableModel.setDataVector(mappingDsHocPhan(dsHocPhan), Constants.hocPhanHeader);
        tableModel.fireTableDataChanged();   
        soHocPhanLabel.setText(dsHocPhan.size() + " kết quả");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        genderBtnGroup = new javax.swing.ButtonGroup();
        infoPanel = new javax.swing.JPanel();
        maGvLabel = new javax.swing.JLabel();
        maGvText = new javax.swing.JTextField();
        tenGvLabel = new javax.swing.JLabel();
        tenGvText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        diaChiText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        sdtText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tenDangNhapText = new javax.swing.JTextField();
        namBtn = new javax.swing.JRadioButton();
        namBtn.setActionCommand("nam");
        nuBtn = new javax.swing.JRadioButton();
        nuBtn.setActionCommand("nữ");
        ngaySinhPicker = new com.github.lgooddatepicker.components.DatePicker();
        ngayTaoPicker = new com.github.lgooddatepicker.components.DateTimePicker();
        thayDoiBtn = new javax.swing.JButton();
        doiMatKhauBtn = new javax.swing.JButton();
        dangXuatBtn = new javax.swing.JButton();
        operationsPanel = new javax.swing.JTabbedPane();
        giaoVuPanel = new javax.swing.JPanel();
        giaoVuScrollPane = new javax.swing.JScrollPane();
        giaoVuTable = new JTable(new DefaultTableModel(mappingDsGiaoVu(dsGiaoVu),Constants.giaoVuHeader));
        searchBarText = new javax.swing.JTextField();
        timKiemBtn = new javax.swing.JButton();
        themBtn = new javax.swing.JButton();
        soKetQuaLabel = new javax.swing.JLabel();
        capNhatBtn = new javax.swing.JButton();
        xoaBtn = new javax.swing.JButton();
        monHocPanel = new javax.swing.JPanel();
        timMonHocText = new javax.swing.JTextField();
        timMonHocBtn = new javax.swing.JButton();
        monHocScrollPane = new javax.swing.JScrollPane();
        monHocTable = new JTable(new DefaultTableModel(mappingDsMonHoc(dsMonHoc),Constants.monHocHeader));
        capNhatMonHocText = new javax.swing.JButton();
        themMonHocText = new javax.swing.JButton();
        xoaMonHocText = new javax.swing.JButton();
        soKetQuaMhLabel = new javax.swing.JLabel();
        hocKiPanel = new javax.swing.JPanel();
        hocKiScrollPane = new javax.swing.JScrollPane();
        hocKiTable = new JTable(new DefaultTableModel(mappingDsHocKi(dsHocKi),Constants.hocKiHeader));
        hkhtText = new javax.swing.JLabel();
        themHocKiText = new javax.swing.JButton();
        xoaHocKiBtn = new javax.swing.JButton();
        hkhtBtn = new javax.swing.JButton();
        lopPanel = new javax.swing.JPanel();
        lopScrollPane = new javax.swing.JScrollPane();
        lopTable = new JTable(new DefaultTableModel(mappingDsLop(dsLop),Constants.lopHeader));
        xoaLopBtn = new javax.swing.JButton();
        themLopBtn = new javax.swing.JButton();
        sinhVienPanel = new javax.swing.JPanel();
        timSinhVienText = new javax.swing.JTextField();
        timSinhVienBtn = new javax.swing.JButton();
        sinhVienScrollPane = new javax.swing.JScrollPane();
        sinhVienTable = new JTable(new DefaultTableModel(mappingDsSinhVien(dsSinhVien),Constants.sinhVienHeader));
        themSinhVienBtn = new javax.swing.JButton();
        capNhatSinhVienBtn = new javax.swing.JButton();
        xemMonSvBtn = new javax.swing.JButton();
        soSinhVienLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lopCombo = new javax.swing.JComboBox<>();
        moDKHPPanel = new javax.swing.JPanel();
        kiDKHPScrollpane = new javax.swing.JScrollPane();
        kiDKHPTable = new JTable(new DefaultTableModel(mappingDsKiDKHP(dsKiDKHP),Constants.kiDKHPHeader));
        themKiDKHPBtn = new javax.swing.JButton();
        hocPhanPanel = new javax.swing.JPanel();
        themHocPhan = new javax.swing.JButton();
        timHocPhanText = new javax.swing.JTextField();
        soHocPhanLabel = new javax.swing.JLabel();
        hocPhanScrollPane = new javax.swing.JScrollPane();
        hocPhanTable = new JTable(new DefaultTableModel(mappingDsHocPhan(dsHocPhan),Constants.hocPhanHeader));
        timHocPhanBtn = new javax.swing.JButton();
        xemDangKiBtn = new javax.swing.JButton();
        xoaHocPhanBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Giáo vụ");
        setForeground(java.awt.Color.darkGray);
        setPreferredSize(new java.awt.Dimension(855, 550));
        setSize(new java.awt.Dimension(855, 550));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        infoPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4), javax.swing.BorderFactory.createTitledBorder(null, "Thông tin cá nhân:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12), new java.awt.Color(0, 0, 0)))); // NOI18N
        infoPanel.setLayout(new java.awt.GridBagLayout());

        maGvLabel.setText("Mã giáo vụ:");
        maGvLabel.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(maGvLabel, gridBagConstraints);

        maGvText.setText(this.giaoVu.getMaGv());
        maGvText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        infoPanel.add(maGvText, gridBagConstraints);

        tenGvLabel.setText("Tên giáo vụ:");
        tenGvLabel.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(tenGvLabel, gridBagConstraints);

        tenGvText.setText(this.giaoVu.getHoTen());
        tenGvText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenGvTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        infoPanel.add(tenGvText, gridBagConstraints);

        jLabel1.setText("Địa chỉ:");
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(jLabel1, gridBagConstraints);

        diaChiText.setText(giaoVu.getDiaChi());
        diaChiText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaChiTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        infoPanel.add(diaChiText, gridBagConstraints);

        jLabel2.setText("Số điện thoại:");
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(jLabel2, gridBagConstraints);

        sdtText.setText(this.giaoVu.getSdt());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        infoPanel.add(sdtText, gridBagConstraints);

        jLabel3.setText("Ngày sinh:");
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Giới tính:");
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(jLabel4, gridBagConstraints);

        jLabel6.setText("Ngày tạo:");
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Tên đăng nhập:");
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(jLabel7, gridBagConstraints);

        tenDangNhapText.setEditable(false);
        tenDangNhapText.setText(this.giaoVu.getTaiKhoan().getTenDangNhap());
        tenDangNhapText.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tenDangNhapText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tenDangNhapText.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        infoPanel.add(tenDangNhapText, gridBagConstraints);

        genderBtnGroup.add(namBtn);
        namBtn.setForeground(new java.awt.Color(0, 0, 0));
        namBtn.setSelected(giaoVu.getGioiTinh().equals("nam")
        );
        namBtn.setText("nam");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(namBtn, gridBagConstraints);

        genderBtnGroup.add(nuBtn);
        nuBtn.setForeground(new java.awt.Color(0, 0, 0));
        nuBtn.setSelected(giaoVu.getGioiTinh().equals("nữ")
        );
        nuBtn.setText("nữ");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        infoPanel.add(nuBtn, gridBagConstraints);

        ngaySinhPicker.setDate(DateTimeUtil.convertToLocalDateViaSqlDate(this.giaoVu.getNgaySinh())
        );
        ngaySinhPicker.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        infoPanel.add(ngaySinhPicker, gridBagConstraints);

        ngayTaoPicker.setDateTimePermissive(DateTimeUtil.convertToLocalDateTimeViaSqlTimestamp(this.giaoVu.getTaiKhoan().getNgayTao())
        );
        ngayTaoPicker.setEnabled(false);
        ngayTaoPicker.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ngayTaoPicker.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        infoPanel.add(ngayTaoPicker, gridBagConstraints);

        thayDoiBtn.setText("Thay đổi");
        thayDoiBtn.setForeground(new java.awt.Color(0, 0, 0));
        thayDoiBtn.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        thayDoiBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        thayDoiBtn.setVerifyInputWhenFocusTarget(false);
        thayDoiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thayDoiBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        infoPanel.add(thayDoiBtn, gridBagConstraints);

        doiMatKhauBtn.setText("Đổi mật khẩu");
        doiMatKhauBtn.setForeground(new java.awt.Color(0, 0, 0));
        doiMatKhauBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doiMatKhauBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 80);
        infoPanel.add(doiMatKhauBtn, gridBagConstraints);

        dangXuatBtn.setForeground(new java.awt.Color(0, 0, 0));
        dangXuatBtn.setText("Đăng xuất");
        dangXuatBtn.setToolTipText("");
        dangXuatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangXuatBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 185);
        infoPanel.add(dangXuatBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(infoPanel, gridBagConstraints);

        giaoVuPanel.setLayout(new java.awt.GridBagLayout());

        giaoVuScrollPane.setRowHeaderView(giaoVuTable);
        giaoVuScrollPane.setViewportView(giaoVuTable);

        giaoVuTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        giaoVuTable.setCellSelectionEnabled(true);
        giaoVuTable.setRowHeight(20);
        giaoVuTable.setShowGrid(true);
        giaoVuScrollPane.setViewportView(giaoVuTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        giaoVuPanel.add(giaoVuScrollPane, gridBagConstraints);

        searchBarText.setText("Tìm mã, họ tên, địa chỉ, số điện thoại, giới tính giáo vụ");
        searchBarText.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(20, 50, 0, 100);
        giaoVuPanel.add(searchBarText, gridBagConstraints);

        timKiemBtn.setText("Tìm kiếm");
        timKiemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        giaoVuPanel.add(timKiemBtn, gridBagConstraints);

        themBtn.setText("Thêm");
        themBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        giaoVuPanel.add(themBtn, gridBagConstraints);

        soKetQuaLabel.setText(dsGiaoVu.size() + " kết quả"
        );
        soKetQuaLabel.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 60, 10, 0);
        giaoVuPanel.add(soKetQuaLabel, gridBagConstraints);

        capNhatBtn.setText("Cập nhật");
        capNhatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capNhatBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        giaoVuPanel.add(capNhatBtn, gridBagConstraints);

        xoaBtn.setText("Xóa");
        xoaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(100, 0, 0, 0);
        giaoVuPanel.add(xoaBtn, gridBagConstraints);

        operationsPanel.addTab("Giáo vụ", giaoVuPanel);

        monHocPanel.setLayout(new java.awt.GridBagLayout());

        timMonHocText.setText("Tìm tên, mã môn học");
        timMonHocText.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(20, 50, 0, 100);
        monHocPanel.add(timMonHocText, gridBagConstraints);

        timMonHocBtn.setText("Tìm kiếm");
        timMonHocBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timMonHocBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        monHocPanel.add(timMonHocBtn, gridBagConstraints);

        monHocScrollPane.setRowHeaderView(monHocTable);
        monHocScrollPane.setViewportView(monHocTable);

        monHocTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        monHocTable.setCellSelectionEnabled(true);
        monHocTable.setRowHeight(20);
        monHocTable.setShowGrid(true);
        monHocScrollPane.setViewportView(monHocTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        monHocPanel.add(monHocScrollPane, gridBagConstraints);

        capNhatMonHocText.setText("Cập nhật");
        capNhatMonHocText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capNhatMonHocTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        monHocPanel.add(capNhatMonHocText, gridBagConstraints);

        themMonHocText.setText("Thêm");
        themMonHocText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themMonHocTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        monHocPanel.add(themMonHocText, gridBagConstraints);

        xoaMonHocText.setText("Xóa");
        xoaMonHocText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaMonHocTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(100, 0, 0, 0);
        monHocPanel.add(xoaMonHocText, gridBagConstraints);

        soKetQuaMhLabel.setText(dsMonHoc.size() + " kết quả"
        );
        soKetQuaMhLabel.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 60, 10, 0);
        monHocPanel.add(soKetQuaMhLabel, gridBagConstraints);

        operationsPanel.addTab("Môn học", monHocPanel);

        hocKiPanel.setLayout(new java.awt.GridBagLayout());

        hocKiScrollPane.setRowHeaderView(hocKiTable);
        hocKiScrollPane.setViewportView(hocKiTable);

        hocKiTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        hocKiTable.setCellSelectionEnabled(true);
        hocKiTable.setRowHeight(20);
        hocKiTable.setShowGrid(true);
        hocKiScrollPane.setViewportView(hocKiTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        hocKiPanel.add(hocKiScrollPane, gridBagConstraints);

        hkhtText.setText("Học kì hiện tại: " + HocKiHienTaiDAO.layThongTinHKHT().getHocki().getTenHocKi()+ " " + HocKiHienTaiDAO.layThongTinHKHT().getHocki().getNamHoc());
        hkhtText.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 60, 10, 0);
        hocKiPanel.add(hkhtText, gridBagConstraints);

        themHocKiText.setText("Thêm");
        themHocKiText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themHocKiTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        hocKiPanel.add(themHocKiText, gridBagConstraints);

        xoaHocKiBtn.setText("Xóa");
        xoaHocKiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaHocKiBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(100, 0, 0, 0);
        hocKiPanel.add(xoaHocKiBtn, gridBagConstraints);

        hkhtBtn.setText("Chọn làm HKHT");
        hkhtBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hkhtBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        hocKiPanel.add(hkhtBtn, gridBagConstraints);

        operationsPanel.addTab("Học kì", hocKiPanel);

        lopPanel.setLayout(new java.awt.GridBagLayout());

        lopScrollPane.setRowHeaderView(lopTable);
        lopScrollPane.setViewportView(lopTable);

        lopTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        lopTable.setCellSelectionEnabled(true);
        lopTable.setRowHeight(20);
        lopTable.setShowGrid(true);
        lopScrollPane.setViewportView(lopTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 10);
        lopPanel.add(lopScrollPane, gridBagConstraints);

        xoaLopBtn.setText("Xóa");
        xoaLopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaLopBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.insets = new java.awt.Insets(80, 0, 0, 0);
        lopPanel.add(xoaLopBtn, gridBagConstraints);

        themLopBtn.setText("Thêm");
        themLopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themLopBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.05;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        lopPanel.add(themLopBtn, gridBagConstraints);

        operationsPanel.addTab("Lớp học", lopPanel);

        sinhVienPanel.setLayout(new java.awt.GridBagLayout());

        timSinhVienText.setText("Tìm mã, họ tên, địa chỉ, số điện thoại, giới tính sinh viên");
        timSinhVienText.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(20, 300, 0, 100);
        sinhVienPanel.add(timSinhVienText, gridBagConstraints);

        timSinhVienBtn.setText("Tìm kiếm");
        timSinhVienBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timSinhVienBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        sinhVienPanel.add(timSinhVienBtn, gridBagConstraints);

        sinhVienScrollPane.setRowHeaderView(sinhVienTable);
        sinhVienScrollPane.setViewportView(sinhVienTable);

        sinhVienTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        sinhVienTable.setCellSelectionEnabled(true);
        sinhVienTable.setRowHeight(20);
        sinhVienTable.setShowGrid(true);
        sinhVienScrollPane.setViewportView(sinhVienTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        sinhVienPanel.add(sinhVienScrollPane, gridBagConstraints);

        themSinhVienBtn.setText("Thêm");
        themSinhVienBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themSinhVienBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        sinhVienPanel.add(themSinhVienBtn, gridBagConstraints);

        capNhatSinhVienBtn.setText("Cập nhật");
        capNhatSinhVienBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capNhatSinhVienBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        sinhVienPanel.add(capNhatSinhVienBtn, gridBagConstraints);

        xemMonSvBtn.setText("Xem môn");
        xemMonSvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xemMonSvBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(100, 0, 0, 0);
        sinhVienPanel.add(xemMonSvBtn, gridBagConstraints);

        soSinhVienLabel.setText(dsSinhVien.size() + " kết quả"
        );
        soSinhVienLabel.setForeground(new java.awt.Color(0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 60, 10, 0);
        sinhVienPanel.add(soSinhVienLabel, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Lớp:");
        jLabel5.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 50, 0, 0);
        sinhVienPanel.add(jLabel5, gridBagConstraints);

        lopCombo.setModel(new javax.swing.DefaultComboBoxModel<>(layDsTenLop(dsLop)));
        lopCombo.setMinimumSize(new java.awt.Dimension(200, 26));
        lopCombo.setPreferredSize(new java.awt.Dimension(200, 26));
        lopCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lopComboActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 80, 0, 0);
        sinhVienPanel.add(lopCombo, gridBagConstraints);

        operationsPanel.addTab("Sinh viên", sinhVienPanel);

        moDKHPPanel.setLayout(new java.awt.GridBagLayout());

        kiDKHPScrollpane.setRowHeaderView(kiDKHPTable);
        kiDKHPScrollpane.setViewportView(kiDKHPTable);

        kiDKHPTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        kiDKHPTable.setCellSelectionEnabled(true);
        kiDKHPTable.setRowHeight(20);
        kiDKHPTable.setShowGrid(true);
        kiDKHPScrollpane.setViewportView(kiDKHPTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        moDKHPPanel.add(kiDKHPScrollpane, gridBagConstraints);

        themKiDKHPBtn.setText("Thêm kì DKHP mới");
        themKiDKHPBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themKiDKHPBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        moDKHPPanel.add(themKiDKHPBtn, gridBagConstraints);

        operationsPanel.addTab("Mở đăng kí học phần", moDKHPPanel);

        hocPhanPanel.setLayout(new java.awt.GridBagLayout());

        themHocPhan.setText("Thêm học phần");
        themHocPhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themHocPhanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        hocPhanPanel.add(themHocPhan, gridBagConstraints);

        timHocPhanText.setText("Tìm mã môn,  tên môn, giáo viên, phòng học");
        timHocPhanText.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(20, 50, 0, 100);
        hocPhanPanel.add(timHocPhanText, gridBagConstraints);

        soHocPhanLabel.setForeground(new java.awt.Color(0, 0, 0));
        soHocPhanLabel.setText(dsHocPhan.size() + " kết quả"
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 60, 10, 0);
        hocPhanPanel.add(soHocPhanLabel, gridBagConstraints);

        hocPhanScrollPane.setRowHeaderView(hocPhanTable);
        hocPhanScrollPane.setViewportView(hocPhanTable);

        hocPhanTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        hocPhanTable.setCellSelectionEnabled(true);
        hocPhanTable.setRowHeight(20);
        hocPhanTable.setShowGrid(true);
        hocPhanScrollPane.setViewportView(hocPhanTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        hocPhanPanel.add(hocPhanScrollPane, gridBagConstraints);

        timHocPhanBtn.setText("Tìm kiếm");
        timHocPhanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timHocPhanBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        hocPhanPanel.add(timHocPhanBtn, gridBagConstraints);

        xemDangKiBtn.setText("Xem đăng kí");
        xemDangKiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xemDangKiBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        hocPhanPanel.add(xemDangKiBtn, gridBagConstraints);

        xoaHocPhanBtn.setText("Xóa học phần");
        xoaHocPhanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaHocPhanBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(100, 0, 0, 0);
        hocPhanPanel.add(xoaHocPhanBtn, gridBagConstraints);

        operationsPanel.addTab("Học phần", hocPhanPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(operationsPanel, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tenGvTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenGvTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenGvTextActionPerformed

    private void diaChiTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaChiTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diaChiTextActionPerformed

    private void thayDoiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thayDoiBtnActionPerformed
        // TODO add your handling code here:
        giaoVu.setMaGv(maGvText.getText());
        giaoVu.setDiaChi(diaChiText.getText());
        giaoVu.setGioiTinh(genderBtnGroup.getSelection().getActionCommand());
        giaoVu.setHoTen(tenGvText.getText());
        giaoVu.setNgaySinh(DateTimeUtil.convertToDateViaLocalDate(ngaySinhPicker.getDate()));
        giaoVu.setSdt(sdtText.getText());
        if (GiaoVuDAO.capNhatThongTin(giaoVu)){
            JOptionPane.showConfirmDialog(null,Constants.SUCCESS,"Cập nhật thành công",JOptionPane.DEFAULT_OPTION);
            updateAllTable("");
        }else{
            JOptionPane.showConfirmDialog(null,Constants.FAIL,"Thông tin không hợp lệ!",JOptionPane.DEFAULT_OPTION);            
        }
    }//GEN-LAST:event_thayDoiBtnActionPerformed

    private void doiMatKhauBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doiMatKhauBtnActionPerformed
        // TODO add your handling code here:
        new LoginForm().setVisible(true);
        new ChangePasswordDialog(this.giaoVu.getTaiKhoan()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_doiMatKhauBtnActionPerformed

    private void dangXuatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangXuatBtnActionPerformed
        // TODO add your handling code here:
        this.giaoVu = null;
        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dangXuatBtnActionPerformed

    private void timKiemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemBtnActionPerformed
        // TODO add your handling code here:
        updateTable(searchBarText.getText());
    }//GEN-LAST:event_timKiemBtnActionPerformed

    private void themBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtnActionPerformed
        // TODO add your handling code here:
        new AddOrUpdateGiaoVu(Constants.ADD_MODE, 0, this).setVisible(true);
    }//GEN-LAST:event_themBtnActionPerformed

    private void capNhatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capNhatBtnActionPerformed
        // TODO add your handling code here:
        int index = giaoVuTable.getSelectedRow();
        if (index>=0){
            GiaoVu gv = dsGiaoVu.get(index);
            new AddOrUpdateGiaoVu(Constants.UPDATE_MODE, gv.getIdGv(), this).setVisible(true);
        }
    }//GEN-LAST:event_capNhatBtnActionPerformed

    private void xoaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaBtnActionPerformed
        // TODO add your handling code here:
        int index = giaoVuTable.getSelectedRow();
        if (index>=0){
            TaiKhoan tk = dsGiaoVu.get(index).getTaiKhoan();
            TaiKhoanDAO.xoaTaiKhoan(tk.getIdTk());
            updateAllTable("");
        }
    }//GEN-LAST:event_xoaBtnActionPerformed

    private void timMonHocBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timMonHocBtnActionPerformed
        // TODO add your handling code here:
        updateMonHocTable(timMonHocText.getText());
    }//GEN-LAST:event_timMonHocBtnActionPerformed

    private void capNhatMonHocTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capNhatMonHocTextActionPerformed
        // TODO add your handling code here:
        int index = monHocTable.getSelectedRow();
        if (index>=0){
            MonHoc mh = dsMonHoc.get(index);
            new AddOrUpdateMonHoc(Constants.UPDATE_MODE, mh.getMaMonHoc(), this).setVisible(true);
        }
    }//GEN-LAST:event_capNhatMonHocTextActionPerformed

    private void themMonHocTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themMonHocTextActionPerformed
        // TODO add your handling code here:
        new AddOrUpdateMonHoc(Constants.ADD_MODE, "", this).setVisible(true);
    }//GEN-LAST:event_themMonHocTextActionPerformed

    private void xoaMonHocTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaMonHocTextActionPerformed
        // TODO add your handling code here:
        int index = monHocTable.getSelectedRow();
        if (index>=0){
            MonHoc mh = dsMonHoc.get(index);
            MonHocDAO.xoaMonHoc(mh.getMaMonHoc());
            updateAllTable("");
        }
    }//GEN-LAST:event_xoaMonHocTextActionPerformed

    private void themHocKiTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themHocKiTextActionPerformed
        // TODO add your handling code here:
        new AddHocKi(this).setVisible(true);
    }//GEN-LAST:event_themHocKiTextActionPerformed

    private void xoaHocKiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaHocKiBtnActionPerformed
        // TODO add your handling code here:
        int index = hocKiTable.getSelectedRow();
        if (index>=0){
            HocKi hk = dsHocKi.get(index);
            HocKi hkht = HocKiHienTaiDAO.layThongTinHKHT().getHocki();
            if (Objects.equals(hk.getIdHk(), hkht.getIdHk())){
                JOptionPane.showConfirmDialog(null,Constants.FAIL,"Không thể xóa học kì hiện tại",JOptionPane.DEFAULT_OPTION);                
            }else{
                if(HocKiDAO.xoaHocKi(hk.getIdHk())){
                    JOptionPane.showConfirmDialog(null,Constants.SUCCESS,"Xóa thành công",JOptionPane.DEFAULT_OPTION);                                
                }else{
                    JOptionPane.showConfirmDialog(null,Constants.FAIL,"Xóa thất bại",JOptionPane.DEFAULT_OPTION);                                
                }
            }
            updateAllTable("");
        }        
    }//GEN-LAST:event_xoaHocKiBtnActionPerformed

    private void hkhtBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hkhtBtnActionPerformed
        // TODO add your handling code here:
        int index = hocKiTable.getSelectedRow();
        if (index>=0){
            HocKi hk = dsHocKi.get(index);
            HocKiHienTaiDAO.thayDoiHKHT(hk);
            hkhtText.setText("Học kì hiện tại: " + HocKiHienTaiDAO.layThongTinHKHT().getHocki().getTenHocKi()+ " " + HocKiHienTaiDAO.layThongTinHKHT().getHocki().getNamHoc());
            updateAllTable("");
        }
    }//GEN-LAST:event_hkhtBtnActionPerformed

    private void themLopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themLopBtnActionPerformed
        // TODO add your handling code here:
        String tenLop = JOptionPane.showInputDialog(null, "Nhập tên lớp:", "Thêm lớp", JOptionPane.PLAIN_MESSAGE);
        if (!tenLop.equals("") && tenLop!=null){
            if (LopDAO.taoLop(new Lop(tenLop))>0){
                JOptionPane.showConfirmDialog(null, "Tạo lớp thành công", Constants.SUCCESS, JOptionPane.DEFAULT_OPTION);
                updateAllTable("");
            }else{
                JOptionPane.showConfirmDialog(null, Constants.MISSING_PARAMS, Constants.FAIL, JOptionPane.DEFAULT_OPTION);                
            }
        }
    }//GEN-LAST:event_themLopBtnActionPerformed

    private void xoaLopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaLopBtnActionPerformed
        // TODO add your handling code here:
        int index = lopTable.getSelectedRow();
        if (index>=0){
            Lop l = dsLop.get(index);
            LopDAO.xoaLop(l.getIdLop());
            updateAllTable("");
        }        
    }//GEN-LAST:event_xoaLopBtnActionPerformed

    private void timSinhVienBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timSinhVienBtnActionPerformed
        // TODO add your handling code here:
        String keyword = timSinhVienText.getText();
        updateSinhVienTable(keyword);
    }//GEN-LAST:event_timSinhVienBtnActionPerformed

    private void themSinhVienBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themSinhVienBtnActionPerformed
        // TODO add your handling code here:
        new AddOrUpdateSinhVien(Constants.ADD_MODE, 0, this).setVisible(true);
    }//GEN-LAST:event_themSinhVienBtnActionPerformed

    private void capNhatSinhVienBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capNhatSinhVienBtnActionPerformed
        // TODO add your handling code here:
        int index = sinhVienTable.getSelectedRow();
        if (index>=0){
            SinhVien s = dsSinhVien.get(index);
            new AddOrUpdateSinhVien(Constants.UPDATE_MODE, s.getIdSv(), this).setVisible(true);
        }
    }//GEN-LAST:event_capNhatSinhVienBtnActionPerformed

    private void xemMonSvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xemMonSvBtnActionPerformed
        // TODO add your handling code here:
        SinhVien sv = dsSinhVien.get(sinhVienTable.getSelectedRow());
        new XemMon(sv).setVisible(true);
    }//GEN-LAST:event_xemMonSvBtnActionPerformed

    private void lopComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lopComboActionPerformed
        // TODO add your handling code here:
        String key = (String)lopCombo.getSelectedItem();
        updateSinhVienTable("");
        lopCombo.setSelectedItem(key);
    }//GEN-LAST:event_lopComboActionPerformed

    private void themKiDKHPBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themKiDKHPBtnActionPerformed
        // TODO add your handling code here:
        new AddKiDKHP(this).setVisible(true);
    }//GEN-LAST:event_themKiDKHPBtnActionPerformed

    private void themHocPhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themHocPhanActionPerformed
        // TODO add your handling code here:
        new AddHocPhan(this).setVisible(true);
    }//GEN-LAST:event_themHocPhanActionPerformed

    private void timHocPhanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timHocPhanBtnActionPerformed
        // TODO add your handling code here:
        String keyword = timHocPhanText.getText();
        updateHocPhanTable(keyword);
    }//GEN-LAST:event_timHocPhanBtnActionPerformed

    private void xemDangKiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xemDangKiBtnActionPerformed
        // TODO add your handling code here:
        HocPhan hp = dsHocPhan.get(hocPhanTable.getSelectedRow());
        new DsSvTrongHp(hp).setVisible(true);
    }//GEN-LAST:event_xemDangKiBtnActionPerformed

    private void xoaHocPhanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaHocPhanBtnActionPerformed
        // TODO add your handling code here:
        HocPhan hp = dsHocPhan.get(hocPhanTable.getSelectedRow());
        HocPhanDAO.xoaHocPhan(hp.getMaHocPhan());
        updateAllTable("");
    }//GEN-LAST:event_xoaHocPhanBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton capNhatBtn;
    private javax.swing.JButton capNhatMonHocText;
    private javax.swing.JButton capNhatSinhVienBtn;
    private javax.swing.JButton dangXuatBtn;
    private javax.swing.JTextField diaChiText;
    private javax.swing.JButton doiMatKhauBtn;
    private javax.swing.ButtonGroup genderBtnGroup;
    private javax.swing.JPanel giaoVuPanel;
    private javax.swing.JScrollPane giaoVuScrollPane;
    private javax.swing.JTable giaoVuTable;
    private javax.swing.JButton hkhtBtn;
    private javax.swing.JLabel hkhtText;
    private javax.swing.JPanel hocKiPanel;
    private javax.swing.JScrollPane hocKiScrollPane;
    private javax.swing.JTable hocKiTable;
    private javax.swing.JPanel hocPhanPanel;
    private javax.swing.JScrollPane hocPhanScrollPane;
    private javax.swing.JTable hocPhanTable;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane kiDKHPScrollpane;
    private javax.swing.JTable kiDKHPTable;
    private javax.swing.JComboBox<String> lopCombo;
    private javax.swing.JPanel lopPanel;
    private javax.swing.JScrollPane lopScrollPane;
    private javax.swing.JTable lopTable;
    private javax.swing.JLabel maGvLabel;
    private javax.swing.JTextField maGvText;
    private javax.swing.JPanel moDKHPPanel;
    private javax.swing.JPanel monHocPanel;
    private javax.swing.JScrollPane monHocScrollPane;
    private javax.swing.JTable monHocTable;
    private javax.swing.JRadioButton namBtn;
    private com.github.lgooddatepicker.components.DatePicker ngaySinhPicker;
    private com.github.lgooddatepicker.components.DateTimePicker ngayTaoPicker;
    private javax.swing.JRadioButton nuBtn;
    private javax.swing.JTabbedPane operationsPanel;
    private javax.swing.JTextField sdtText;
    private javax.swing.JTextField searchBarText;
    private javax.swing.JPanel sinhVienPanel;
    private javax.swing.JScrollPane sinhVienScrollPane;
    private javax.swing.JTable sinhVienTable;
    private javax.swing.JLabel soHocPhanLabel;
    private javax.swing.JLabel soKetQuaLabel;
    private javax.swing.JLabel soKetQuaMhLabel;
    private javax.swing.JLabel soSinhVienLabel;
    private javax.swing.JTextField tenDangNhapText;
    private javax.swing.JLabel tenGvLabel;
    private javax.swing.JTextField tenGvText;
    private javax.swing.JButton thayDoiBtn;
    private javax.swing.JButton themBtn;
    private javax.swing.JButton themHocKiText;
    private javax.swing.JButton themHocPhan;
    private javax.swing.JButton themKiDKHPBtn;
    private javax.swing.JButton themLopBtn;
    private javax.swing.JButton themMonHocText;
    private javax.swing.JButton themSinhVienBtn;
    private javax.swing.JButton timHocPhanBtn;
    private javax.swing.JTextField timHocPhanText;
    private javax.swing.JButton timKiemBtn;
    private javax.swing.JButton timMonHocBtn;
    private javax.swing.JTextField timMonHocText;
    private javax.swing.JButton timSinhVienBtn;
    private javax.swing.JTextField timSinhVienText;
    private javax.swing.JButton xemDangKiBtn;
    private javax.swing.JButton xemMonSvBtn;
    private javax.swing.JButton xoaBtn;
    private javax.swing.JButton xoaHocKiBtn;
    private javax.swing.JButton xoaHocPhanBtn;
    private javax.swing.JButton xoaLopBtn;
    private javax.swing.JButton xoaMonHocText;
    // End of variables declaration//GEN-END:variables
}
