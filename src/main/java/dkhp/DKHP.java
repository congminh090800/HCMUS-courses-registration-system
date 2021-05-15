/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dkhp;

import hocki.HocKi;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import vaitro.VaiTro;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="DKHP")
public class DKHP {
    private Integer idDkhp;
    private Date ngayBatDau,ngayKetThuc;
    private HocKi hocKi;

    @Id
    @Column(name = "id_dkhp")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdDkhp() {
        return idDkhp;
    }

    public void setIdDkhp(Integer idDkhp) {
        this.idDkhp = idDkhp;
    }

    @Column(name="ngay_bat_dau",nullable = false)
    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    @Column(name="ngay_ket_thuc",nullable = false)
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoc_ki")
    public HocKi getHocKi() {
        return hocKi;
    }

    public void setHocKi(HocKi hocKi) {
        this.hocKi = hocKi;
    }

    public DKHP(String ngayBatDau, String ngayKetThuc) {
        try {
            this.ngayBatDau = new java.sql.Date(new SimpleDateFormat("yyyyMMdd").parse(ngayBatDau).getTime());
            this.ngayKetThuc = new java.sql.Date(new SimpleDateFormat("yyyyMMdd").parse(ngayKetThuc).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VaiTro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DKHP(){}
}
