/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hocki;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="HOCKIHIENTAI")
public class HocKiHienTai {
    private Integer hkht;
    private HocKi hocki;

    @Id  
    public Integer getHkht() {
        return hkht;
    }

    public void setHkht(Integer hkht) {
        this.hkht = hkht;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "hkht")
    public HocKi getHocki() {
        return hocki;
    }

    public void setHocki(HocKi hocki) {
        this.hocki = hocki;
    }

    public HocKiHienTai() {
    }

    public HocKiHienTai(HocKi hocki) {
        this.hocki = hocki;
    }
    
}
