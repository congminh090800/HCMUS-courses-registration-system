/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taikhoan;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import vaitro.GiaoVu;

/**
 *
 * @author Admin
 */

@Entity
@DiscriminatorValue("giáo vụ")
public class TKGiaoVu extends TaiKhoan {   
    private GiaoVu giaovu;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    public GiaoVu getGiaovu() {
        return giaovu;
    }
    public void setGiaovu(GiaoVu giaovu) {
        this.giaovu = giaovu;
    }

    public TKGiaoVu() {
    }

    public TKGiaoVu(String tenDangNhap, String matKhau, String loaiTaiKhoan) {
        super(tenDangNhap, matKhau, loaiTaiKhoan);
    }
    
}
