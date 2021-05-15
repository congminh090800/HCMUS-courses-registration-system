/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dkhp.DKHP;
import dkhp.DKHPDAO;
import hocki.HocKi;
import hocki.HocKiDAO;
import hocki.HocKiHienTai;
import hocki.HocKiHienTaiDAO;
import hocphan.HocPhan;
import hocphan.HocPhanDAO;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lop.Lop;
import lop.LopDAO;
import monhoc.MonHoc;
import monhoc.MonHocDAO;
import taikhoan.TaiKhoan;
import taikhoan.TaiKhoanDAO;
import static taikhoan.TaiKhoanDAO.doiMatKhau;
import vaitro.GiaoVu;
import vaitro.GiaoVuDAO;
import constants.Constants;

/**
 *
 * @author Admin
 */
public class main {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        boolean doiMatKhau = TaiKhoanDAO.doiMatKhau(4, "admin");
//        if (doiMatKhau == true){
//            System.out.println("Thanh cong");
//        }else{
//            System.err.println("Khong thanh cong");
//        }
//        TaiKhoan tk = new TaiKhoan("testing", "testing");
//        TaiKhoanDAO.taoTaiKhoan(tk);
//        TaiKhoanDAO.xoaTaiKhoan(4);
//        if (xoaTaiKhoan){
//            System.out.println("Thanh cong");
//        }else{
//            System.err.println("Khong thanh cong");
//        }
        //GiaoVuDAO.themGiaoVu(new GiaoVu("GV003","Lee B","Hồ Chí Minh","0573419213","20000809","nam"));
        // GiaoVuDAO.xoaGiaoVu(4);
//        List<GiaoVu> dsGiaoVu = GiaoVuDAO.layDanhSach("");
//        dsGiaoVu.forEach((giaoVu) -> {
//            System.out.println(giaoVu.getHoTen() + giaoVu.getTaiKhoan().getTenDangNhap());
//        });
//        List<Lop> dsLop=LopDAO.dsLop();
//        dsLop.forEach((lop) -> {
//            System.out.println(lop.getTenLop());
//        });
//        Lop lop = LopDAO.timLop(1);
//        lop.getDsSinhVien().forEach((sinhvien) -> {
//            System.out.println(sinhvien.getHoTen());
//        });
        //LopDAO.taoLop(new Lop("17CSH1"));
        //HocKiDAO.taoHocKi(new HocKi(2021,"HK1","20200801","20210201"));
        //HocKiDAO.taoHocKi(new HocKi(2021,"HK2","20210202","20210801"));
//          HocKi hk = HocKiDAO.timHocKi(1);
//          HocKiHienTaiDAO.thayDoiHKHT(hk);
//          HocKi hk2 = HocKiHienTaiDAO.layThongTinHKHT().getHocki();
//          System.out.println(hk2.getTenHocKi()+hk2.getNamHoc().toString());
//          HocKi hk3 = HocKiDAO.timHocKi(2);
//          HocKiHienTaiDAO.thayDoiHKHT(hk3);
//          System.out.println(hk3.getTenHocKi()+hk2.getNamHoc().toString());
//          HocKiDAO.xoaHocKi(2);
//          HocKiDAO.xoaHocKi(1);
//           HocKi hocKi = HocKiHienTaiDAO.layThongTinHKHT().getHocki();
//           DKHP dk = new DKHP("20200801","20200814");
//           dk.setHocKi(hocKi);
//           DKHP dk2 = new DKHP("20210301","20210314");
//           dk2.setHocKi(hocKi);
//           DKHPDAO.taoKiDKHP(dk);
//           DKHPDAO.taoKiDKHP(dk2);
//           DKHPDAO.layDanhSach().forEach((DKHP) -> {
//               System.out.println(DKHP.getHocKi().getNamHoc()+" "+DKHP.getHocKi().getTenHocKi()+" "+DKHP.getIdDkhp().toString());
//           });
//            MonHoc monHoc = MonHocDAO.timMonHoc("NMCNTT");
//            HocPhan hocPhan = new HocPhan(3, 150, "Le Hao", "F102", Constants.MON);
//            HocPhan hocPhan2 = new HocPhan(1, 150, "Nguyen Ha", "D202", Constants.FRI);
//            HocPhanDAO.taoHocPhan(hocPhan,monHoc);
//            HocPhanDAO.taoHocPhan(hocPhan2,monHoc);
//            HocPhanDAO.layDanhSach("NMCNTT").forEach((hp) -> {
//                System.out.println(hp.getMaHocPhan()+" "+hp.getMonHoc().getTenMonHoc()+" "+hp.getHocKi().getTenHocKi());
//            });
//              System.out.println(LopDAO.demSoLuongSV(1, null));
    }
}
