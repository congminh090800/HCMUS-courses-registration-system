package vaitro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dkhp.SinhVienHocPhan;
import hocphan.HocPhan;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import taikhoan.TaiKhoan;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lop.Lop;

/**
 *
 * @author Admin
 */
@Entity
@Table(
    name="SINHVIEN"   
)
public class SinhVien extends VaiTro{
    private Integer idSv;
    private String mssv;
    private Lop lop;
    private Set<SinhVienHocPhan> dsSVHP = new HashSet<>();
            
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sv")
    public Integer getIdSv() {
        return idSv;
    }

    public void setIdSv(Integer idSv) {
        this.idSv = idSv;
    }

    @Column(name="mssv", nullable=false)
    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lop")
    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVien",targetEntity = SinhVienHocPhan.class)
    public Set<SinhVienHocPhan> getDsSVHP() {
        return dsSVHP;
    }

    public void setDsSVHP(Set<SinhVienHocPhan> dsSVHP) {
        this.dsSVHP = dsSVHP;
    }
    
    public SinhVien(String mssv, String hoTen, String diaChi, String sdt, String ngaySinh, String gioiTinh) {
        super(hoTen, diaChi, sdt, ngaySinh, gioiTinh);
        this.mssv = mssv;
    }
    
    public SinhVien(){}
    
}
