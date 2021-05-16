/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dkhp;

import hocphan.HocPhan;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import vaitro.SinhVien;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="SINHVIEN_HOCPHAN")
public class SinhVienHocPhan {
    private Integer idSvhp;
    private Date ngayDangKi;
    private SinhVien sinhVien;
    private HocPhan hocPhan;
    
    public SinhVienHocPhan(){}

    public SinhVienHocPhan(SinhVien sinhVien, HocPhan hocPhan) {
        this.sinhVien = sinhVien;
        this.hocPhan = hocPhan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_svhp")
    public Integer getIdSvhp() {
        return idSvhp;
    }

    public void setIdSvhp(Integer idSvhp) {
        this.idSvhp = idSvhp;
    }

    @Column(name="ngay_dang_ki",nullable = false)
    @CreationTimestamp
    public Date getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(Date ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
    }

    @ManyToOne
    @JoinColumn(name="sinh_vien")
    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }
    
    @ManyToOne
    @JoinColumn(name="hoc_phan")
    public HocPhan getHocPhan() {
        return hocPhan;
    }

    public void setHocPhan(HocPhan hocPhan) {
        this.hocPhan = hocPhan;
    }
    
    
}
