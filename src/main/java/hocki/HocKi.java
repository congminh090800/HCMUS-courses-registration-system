/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hocki;

import dkhp.DKHP;
import hocphan.HocPhan;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import vaitro.VaiTro;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="HOCKI")
public class HocKi {
    private Integer idHk,namHoc;
    private String tenHocKi;
    private Date ngayBatDau, ngayKetThuc;
    private HocKiHienTai hocKiHienTai;
    private Set<DKHP> dsKiDKHP = new HashSet<>();
    private Set<HocPhan> dsHocPhan = new HashSet<>();
    
    @Id
    @Column(name = "id_hk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdHk() {
        return idHk;
    }

    public void setIdHk(Integer idHk) {
        this.idHk = idHk;
    }

    @Column(name="nam_hoc",nullable = false)
    public Integer getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(Integer namHoc) {
        this.namHoc = namHoc;
    }

    @Column(name="ten_hoc_ki", nullable = false)
    public String getTenHocKi() {
        return tenHocKi;
    }

    public void setTenHocKi(String tenHocKi) {
        this.tenHocKi = tenHocKi;
    }
    
    @Column(name="ngay_bat_dau")
    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    @Column(name="ngay_ket_thuc")
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }   

    public HocKi(Integer namHoc, String tenHocKi, String ngayBatDau, String ngayKetThuc) {
        this.namHoc = namHoc;
        this.tenHocKi = tenHocKi;
        try {
            this.ngayBatDau = new java.sql.Date(new SimpleDateFormat("yyyyMMdd").parse(ngayBatDau).getTime());
            this.ngayKetThuc = new java.sql.Date(new SimpleDateFormat("yyyyMMdd").parse(ngayKetThuc).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VaiTro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HocKi(){
    }
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hocki")
    public HocKiHienTai getHocKiHienTai() {
        return hocKiHienTai;
    }

    public void setHocKiHienTai(HocKiHienTai hocKiHienTai) {
        this.hocKiHienTai = hocKiHienTai;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hocKi", targetEntity = DKHP.class,cascade = CascadeType.ALL)
    public Set<DKHP> getDsKiDKHP() {
        return dsKiDKHP;
    }

    public void setDsKiDKHP(Set<DKHP> dsKiDKHP) {
        this.dsKiDKHP = dsKiDKHP;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hocKi", targetEntity = HocPhan.class, cascade = CascadeType.ALL)
    public Set<HocPhan> getDsHocPhan() {
        return dsHocPhan;
    }

    public void setDsHocPhan(Set<HocPhan> dsHocPhan) {
        this.dsHocPhan = dsHocPhan;
    }
     
}
