/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaitro;

import hocphan.HocPhan;
import java.io.Serializable;
import java.util.List;
import lop.Lop;
import lop.LopDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import taikhoan.TKSinhVien;
import taikhoan.TaiKhoanDAO;
import utils.HibernateUtil;

/**
 *
 * @author Admin
 */
public class SinhVienDAO {
//    public static List<SinhVien> getList(String keyword){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        List<SinhVien> dsSinhVien = null;
//        try {
//            String hql = "select sv from SINHVIEN sv";
//            Query query = session.createQuery(hql);
//            dsSinhVien = query.getResultList();      
//        } catch (HibernateException ex) {
//            System.err.println(ex);
//        } finally {
//            session.close();
//        }
//        return dsSinhVien;
//    }
    public static SinhVien timSinhVien(Integer idSv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        SinhVien sinhVien = null;
        try {
            String hql = """
             select sv from SinhVien sv left join fetch sv.taiKhoan left join fetch sv.lop
             where sv.idSv=:idSv
             """;
            Query query = session.createQuery(hql);
            query.setParameter("idSv",idSv);
            List<SinhVien> dsSV = query.getResultList();
            if (dsSV.size()>0){
                sinhVien = dsSV.get(0);
            }
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }   
        return sinhVien;
    }
     public static SinhVien timSinhVienTK(Integer idTk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        SinhVien sinhVien = null;
        try {
            String hql = """
                         select sv from SinhVien sv left join fetch sv.taiKhoan left join fetch sv.lop
                         where sv.taiKhoan.idTk=:idTk
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("idTk",idTk);
            List<SinhVien> dsSV = query.getResultList();
            if (dsSV.size()>0){
                sinhVien = dsSV.get(0);
            }   
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }   
        return sinhVien;
    }   
    /*  return 0 if operation failed, otherwise return generated id */
    public static Integer themSinhVien(SinhVien sinhVien, Integer idLop){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer result = 0;
        if (timSinhVien(sinhVien.getIdSv())!=null || TaiKhoanDAO.timTaiKhoan(sinhVien.getMssv())!=null){
            return result;
        }else{
            Integer id = TaiKhoanDAO.taoTaiKhoan(new TKSinhVien(sinhVien.getMssv(),sinhVien.getMssv(),"sinh vi??n"));
            TKSinhVien tkSv = (TKSinhVien) TaiKhoanDAO.timTaiKhoan(id);
            sinhVien.setTaiKhoan(tkSv);
            Lop lop = LopDAO.timLop(idLop);
            sinhVien.setLop(lop);
        }
        try{
            session.getTransaction().begin();
            result = (Integer) session.save(sinhVien);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return result;
    }
    public static boolean capNhatThongTin(SinhVien sinhVien){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (timSinhVien(sinhVien.getIdSv())==null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.update(sinhVien);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;
    }
}
