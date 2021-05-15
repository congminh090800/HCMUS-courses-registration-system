/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monhoc;

import hocphan.HocPhan;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="MONHOC")
public class MonHoc {
    private Integer soTinChi;
    private String maMonHoc,tenMonHoc;
    private Set<HocPhan> hocPhan=new HashSet<>();
            
    public MonHoc(Integer soTinChi, String tenMonHoc) {
        this.soTinChi = soTinChi;
        this.tenMonHoc = tenMonHoc;
    }
    
    public MonHoc(){}

    @Id
    @Column(name="ma_mon_hoc",nullable = false,unique = true)
    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }
     
    @Column(name = "so_tin_chi")
    public Integer getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }
    
    @Column(name = "ten_mon_hoc", nullable = false)
    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monHoc")
    public Set<HocPhan> getHocPhan() {
        return hocPhan;
    }

    public void setHocPhan(Set<HocPhan> hocPhan) {
        this.hocPhan = hocPhan;
    }

    
}
