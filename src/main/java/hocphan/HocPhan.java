/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hocphan;

import hocki.HocKi;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import monhoc.MonHoc;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
/**
 *
 * @author Admin
 */
@Entity
@Table(name="HOCPHAN")
public class HocPhan {
    private Integer caHoc,soLuongToiDa;
    private MonHoc monHoc;
    private String  maHocPhan,gvLyThuyet,tenPhongHoc,ngayHoc;
    private HocKi hocKi;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hocphan_seq")
    @GenericGenerator(
            name = "hocphan_seq", 
            strategy = "hocphan.HocPhanIdGenerator", 
            parameters = { 
                @Parameter(name = HocPhanIdGenerator.INCREMENT_PARAM,value = "1"),
                @Parameter(name = HocPhanIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    @Column(name="ma_hoc_phan",nullable = false,unique = true)
    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    @Column(name="ca_hoc", nullable = false)
    public Integer getCaHoc() {
        return caHoc;
    }

    public void setCaHoc(Integer caHoc) {
        this.caHoc = caHoc;
    }

    @Column(name="so_luong_toi_da", nullable = false)
    public Integer getSoLuongToiDa() {
        return soLuongToiDa;
    }

    public void setSoLuongToiDa(Integer soLuongToiDa) {
        this.soLuongToiDa = soLuongToiDa;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mon_hoc",nullable = false)
    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    @Column(name="gv_ly_thuyet",nullable = false)
    public String getGvLyThuyet() {
        return gvLyThuyet;
    }

    public void setGvLyThuyet(String gvLyThuyet) {
        this.gvLyThuyet = gvLyThuyet;
    }

    @Column(name="ten_phong_hoc")
    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    public void setTenPhongHoc(String tenPhongHoc) {
        this.tenPhongHoc = tenPhongHoc;
    }

    @Column(name="ngay_hoc")
    public String getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(String ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoc_ki", nullable = false)
    public HocKi getHocKi() {
        return hocKi;
    }

    public void setHocKi(HocKi hocKi) {
        this.hocKi = hocKi;
    }

    public HocPhan() {
    }

    public HocPhan(Integer caHoc, Integer soLuongToiDa, String gvLyThuyet, String tenPhongHoc, String ngayHoc) {
        this.caHoc = caHoc;
        this.soLuongToiDa = soLuongToiDa;
        this.gvLyThuyet = gvLyThuyet;
        this.tenPhongHoc = tenPhongHoc;
        this.ngayHoc = ngayHoc;
    }
    
}
