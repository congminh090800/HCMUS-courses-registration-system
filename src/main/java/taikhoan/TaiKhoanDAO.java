/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taikhoan;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

/**
 *
 * @author Admin
 */
public class TaiKhoanDAO {
    public static List<TaiKhoan> layDanhSach(String keyword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<TaiKhoan> dsTaiKhoan = null;
        try {
            String hql ="select tk from TaiKhoan tk\n"+
                (keyword.equals("")?"":"where tk.tenDangNhap like :keyword");
            Query query = session.createQuery(hql);
            if (!keyword.equals("")){
                query.setParameter("keyword", "%"+keyword+"%");   
            }
            dsTaiKhoan = query.getResultList();      
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dsTaiKhoan;
    }
    
    public static TaiKhoan timTaiKhoan(String tenDangNhap){
        Session session = HibernateUtil.getSessionFactory().openSession();
        TaiKhoan taiKhoan = null;
        try {
            String hql = """
                         select tk from TaiKhoan tk
                         where tk.tenDangNhap=:tenDangNhap
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("tenDangNhap",tenDangNhap);
            List<TaiKhoan> dsTK = query.getResultList();
            if (dsTK.size()>0){
                taiKhoan = dsTK.get(0);
            }   
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return taiKhoan;
    }
    
    public static TaiKhoan timTaiKhoan(Integer idTk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        TaiKhoan taiKhoan = null;
        try {
            taiKhoan = (TaiKhoan) session.get(TaiKhoan.class,(Serializable)idTk);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }   
        return taiKhoan;
    }
    
    /*  return 0 if operation failed, otherwise return generated id */    
    public static Integer taoTaiKhoan(TaiKhoan tk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer result = 0;
        String type = tk.getLoaiTaiKhoan();
        if (timTaiKhoan(tk.getTenDangNhap())!= null) return result;
        try {
            session.getTransaction().begin();
            if (type.equals("giáo vụ")){
                result = (Integer) session.save(tk);                
            }else if (type.equals("sinh viên")){
                result = (Integer) session.save(tk);
            }
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            System.err.println(ex);
        }
        return result;
    }
    public static boolean doiMatKhau(Integer idTk, String matKhau){
        Session session = HibernateUtil.getSessionFactory().openSession();
        TaiKhoan tk = timTaiKhoan(idTk);
        if (tk == null){
            return false;
        }else{
            tk.setMatKhau(matKhau);
        }
        try{
            session.getTransaction().begin();
            session.update(tk);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }
        return true;
    }
    
    public static boolean xoaTaiKhoan(Integer idTk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        TaiKhoan tk = timTaiKhoan(idTk);
        if (tk == null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.delete(tk);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }
        return true;        
    }
}
