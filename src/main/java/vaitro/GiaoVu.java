/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaitro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="GIAOVU")
public class GiaoVu extends VaiTro{
    private Integer idGv;
    private String maGv;

    public GiaoVu(String maGv, String hoTen, String diaChi, String sdt, String ngaySinh, String gioiTinh) {
        super(hoTen, diaChi, sdt, ngaySinh, gioiTinh);
        this.maGv = maGv;
    }

    public GiaoVu(){}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_gv")
    public Integer getIdGv() {
        return idGv;
    }

    public void setIdGv(Integer idGv) {
        this.idGv = idGv;
    }

    @Column(name="ma_gv", nullable=false)
    public String getMaGv() {
        return maGv;
    }

    public void setMaGv(String maGv) {
        this.maGv = maGv;
    }
    
}
