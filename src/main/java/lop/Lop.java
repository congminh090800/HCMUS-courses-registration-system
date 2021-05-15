/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lop;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import vaitro.SinhVien;

/**
 *
 * @author Admin
 */

@Entity
@Table(name="LOP")
public class Lop {
    private Integer idLop;
    private String tenLop;
    private Set<SinhVien> dsSinhVien = new HashSet<>();
    
    @Id
    @Column(name = "id_lop")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdLop() {
        return idLop;
    }
    
    public void setIdLop(Integer idLop) {
        this.idLop = idLop;
    }
    
    @Column(name = "ten_lop", nullable = false)
    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lop")
    public Set<SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }
    
    public void setDsSinhVien(Set<SinhVien> dsSinhVien) {
        this.dsSinhVien = dsSinhVien;
    }

    public Lop(String tenLop) {
        this.tenLop = tenLop;
    }
    
    public Lop(){}
}
