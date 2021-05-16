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
import dkhp.SinhVienHocPhanDAO;
import vaitro.SinhVien;
import vaitro.SinhVienDAO;

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
//            MonHoc monHoc2 = MonHocDAO.timMonHoc("VLDC");
//            MonHoc monHoc3 = MonHocDAO.timMonHoc("HDH");
//            HocPhan hocPhan = new HocPhan(3, 150, "Le Hao", "F102", Constants.MON);
//            HocPhan hocPhan2 = new HocPhan(1, 150, "Nguyen Ha", "D202", Constants.FRI);
//            HocPhan hocPhan3 = new HocPhan(1, 100, "Nguyen Ha", "D203", Constants.FRI);
//            HocPhan hocPhan4 = new HocPhan(4, 150, "Tran Ba", "D103", Constants.SAT);
//            HocPhanDAO.taoHocPhan(hocPhan,monHoc);
//            HocPhanDAO.taoHocPhan(hocPhan2,monHoc);
//            HocPhanDAO.taoHocPhan(hocPhan3,monHoc2);
//            HocPhanDAO.taoHocPhan(hocPhan4,monHoc3);
//            HocPhanDAO.layDanhSach("NMCNTT").forEach((hp) -> {
//                System.out.println(hp.getMaHocPhan()+" "+hp.getMonHoc().getTenMonHoc()+" "+hp.getHocKi().getTenHocKi());
//            });

//              System.out.println(LopDAO.demSoLuongSV(1, null));

//               HocPhan hocPhan = HocPhanDAO.timHocPhan("NMCNTT00015");
//
//               SinhVien sinhVien = SinhVienDAO.timSinhVien(1);
//               Integer soLuong = SinhVienHocPhanDAO.kiemTraSoLuongMon(sinhVien);
//               System.out.println("So mon da dang ki:"+soLuong);
//                boolean daDangKi = SinhVienHocPhanDAO.daDangKiMon(sinhVien, hocPhan);
//                if (daDangKi){
//                    System.out.println("da dang ki mon nay");
//                }else

//               HocPhan hp = HocPhanDAO.timHocPhan("HDH00022");
                HocPhan hp2 = HocPhanDAO.timHocPhan("NMCNTT00019");
                SinhVien sv = SinhVienDAO.timSinhVien(1);
//                SinhVienHocPhanDAO.dangKiHocPhan(sv, hp);
//                SinhVienHocPhanDAO.dangKiHocPhan(sv, hp2);      

        //SinhVienHocPhanDAO.huyDKHP(1);
//        SinhVienHocPhanDAO.xemHpCuaSv(sv).forEach((each) -> {
//            System.out.println(each.getMaHocPhan());
//        });
//        
//        SinhVienHocPhanDAO.xemSvTrongHp(hp2).forEach((each) -> {
//            System.out.println(each.getHoTen());
//        });
//        SinhVienHocPhanDAO.xemMonDaDangKi(sv).forEach((each) -> {
//            System.out.println(each.getTenMonHoc());
//        });
//            Lop lop = LopDAO.timLop(1);
//            LopDAO.timSvTrongLop(lop, "18120463").forEach((t) -> {
//                System.out.println(t.getHoTen());
//            });
    }
}
