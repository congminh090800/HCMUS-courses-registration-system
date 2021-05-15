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
import vaitro.SinhVien;

/**
 *
 * @author Admin
 */
@Entity
@DiscriminatorValue("sinh viên")
public class TKSinhVien extends TaiKhoan {
    private SinhVien sinhvien;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "taiKhoan",cascade = CascadeType.ALL)
    public SinhVien getSinhvien() {
        return sinhvien;
    }
    public void setSinhvien(SinhVien sinhvien) {
        this.sinhvien = sinhvien;
    }

    public TKSinhVien() {
    }

    public TKSinhVien(String tenDangNhap, String matKhau, String loaiTaiKhoan) {
        super(tenDangNhap, matKhau, loaiTaiKhoan);
    }
    
}
