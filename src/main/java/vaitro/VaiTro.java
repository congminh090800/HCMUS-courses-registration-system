/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaitro;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import taikhoan.TaiKhoan;

/**
 *
 * @author Admin
 */
@MappedSuperclass
abstract public class VaiTro {
    protected String hoTen, diaChi, sdt, gioiTinh;
    private TaiKhoan taiKhoan;
    private Date ngaySinh;
    
    public VaiTro(){}

    public VaiTro(String hoTen, String diaChi, String sdt, String ngaySinh, String gioiTinh) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        try {
            this.ngaySinh = new java.sql.Date(new SimpleDateFormat("yyyyMMdd").parse(ngaySinh).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VaiTro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tai_khoan",referencedColumnName = "id_tk")
    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
    
    @Column(name="ho_ten")
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    @Column(name="dia_chi")
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Column(name="sdt")
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Column(name="ngay_sinh")
    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Column(name="gioi_tinh", nullable=false)
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
