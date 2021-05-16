/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaitro;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import taikhoan.TKGiaoVu;
import taikhoan.TaiKhoan;
import taikhoan.TaiKhoanDAO;
import utils.HibernateUtil;

/**
 *
 * @author Admin
 */
public class GiaoVuDAO {
    public static List<GiaoVu> layDanhSach(String keyword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<GiaoVu> dsGiaoVu = null;
        try {
            String hql ="from GiaoVu gv\n"+
                        (keyword.equals("")?"":"""
                                               where gv.hoTen like :keyword or gv.maGv like :keyword
                                               or gv.diaChi like :keyword or gv.sdt like :keyword
                                               or gv.gioiTinh like :keyword
                                               """);
            Query query = session.createQuery(hql);
            if (!keyword.equals("")){
                query.setParameter("keyword", "%"+keyword+"%");   
            }
            dsGiaoVu = query.getResultList();      
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dsGiaoVu;
    }
    
    public static GiaoVu timGiaoVu(String maGv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        GiaoVu giaoVu = null;
        try {
            String hql = """
                         select gv from GiaoVu gv
                         where gv.maGv=:maGv
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("maGv",maGv);
            List<GiaoVu> dsGV = query.getResultList();
            if (dsGV.size()>0){
                giaoVu = dsGV.get(0);
            }   
        } catch (HibernateException ex) {
            System.err.println(ex);
        } 
        return giaoVu;
    }
    
    public static GiaoVu timGiaoVu(Integer idGv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        GiaoVu giaoVu = null;
        try {
            giaoVu = (GiaoVu) session.get(GiaoVu.class,(Serializable)idGv);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }   
        return giaoVu;
    }
    
    /*  return 0 if operation failed, otherwise return generated id */
    public static Integer themGiaoVu(GiaoVu giaoVu){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer result = 0;
        if (timGiaoVu(giaoVu.getIdGv())!=null || TaiKhoanDAO.timTaiKhoan(giaoVu.getMaGv())!=null){
            return result;
        }else{
            Integer id = TaiKhoanDAO.taoTaiKhoan(new TKGiaoVu(giaoVu.getMaGv(),giaoVu.getMaGv(),"giáo vụ"));
            TKGiaoVu tkGv = (TKGiaoVu) TaiKhoanDAO.timTaiKhoan(id);
            giaoVu.setTaiKhoan(tkGv);
        }
        try{
            session.getTransaction().begin();
            result = (Integer) session.save(giaoVu);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return result;
    }
    public static boolean capNhatThongTin(GiaoVu giaoVu){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (GiaoVuDAO.timGiaoVu(giaoVu.getIdGv())==null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.update(giaoVu);
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
