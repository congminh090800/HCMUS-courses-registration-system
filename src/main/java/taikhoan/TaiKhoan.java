/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taikhoan;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="TAIKHOAN")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="loai_tai_khoan", 
  discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "unknown")
public class TaiKhoan{
    private Integer idTk;
    private String tenDangNhap, matKhau, loaiTaiKhoan;
    private Timestamp ngayTao;
    
    public TaiKhoan(){}
    public TaiKhoan(String tenDangNhap, String matKhau, String loaiTaiKhoan) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tk")
    public Integer getIdTk() {
        return idTk;
    }

    public void setIdTk(Integer idTk) {
        this.idTk = idTk;
    }
        
    @Column(name="ten_dang_nhap", nullable=false, unique=true)
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    
    @Column(name="mat_khau", nullable=false)
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    @CreationTimestamp
    @Column(name="ngay_tao")
    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }  
    
    @Column(name="loai_tai_khoan",insertable = false,updatable = false)
    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
}
