/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monhoc;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import taikhoan.TKGiaoVu;
import taikhoan.TaiKhoanDAO;
import utils.HibernateUtil;
import vaitro.GiaoVu;
import vaitro.GiaoVuDAO;

/**
 *
 * @author Admin
 */
public class MonHocDAO {
    public static List<MonHoc> layDanhSach(String keyword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<MonHoc> dsMonHoc = null;
        try {
            String hql ="select mh from MonHoc mh\n"+
                        (keyword.equals("")?"":"""
                                               where mh.tenMonHoc like :keyword
                                               """);
            Query query = session.createQuery(hql);
            if (!keyword.equals("")){
                query.setParameter("keyword", "%"+keyword+"%");   
            }
            dsMonHoc = query.getResultList();      
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dsMonHoc;
    }
    
    public static MonHoc timMonHoc(String maMonHoc){
        Session session = HibernateUtil.getSessionFactory().openSession();
        MonHoc monHoc = null;
        try {
            String hql="""
                       select mh from MonHoc mh 
                       where mh.maMonHoc=:maMonHoc
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("maMonHoc", maMonHoc);
            monHoc = (MonHoc) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return monHoc;
    }
    
    /*  return 0 if operation failed, otherwise return generated id */
    public static boolean taoMonHoc(MonHoc monHoc){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (timMonHoc(monHoc.getMaMonHoc())!=null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.save(monHoc);
            session.getTransaction().commit();
        }catch(HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;
    }
    public static boolean capNhatThongTin(MonHoc monHoc){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (timMonHoc(monHoc.getMaMonHoc())==null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.update(monHoc);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;
    }    
    public static boolean xoaMonHoc(String maMonHoc){
        Session session = HibernateUtil.getSessionFactory().openSession();
        MonHoc mh = timMonHoc(maMonHoc);
        if (mh == null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.delete(mh);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }
        return true;        
    }
}
