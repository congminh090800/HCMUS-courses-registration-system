/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lop;

import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

/**
 *
 * @author Admin
 */
public class LopDAO {
    public static List<Lop> dsLop(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Lop> dsLop = null;
        try {
            String hql ="select l from Lop l";
            Query query = session.createQuery(hql);
            dsLop = query.getResultList();      
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dsLop;        
    }
    
    public static Lop timLop(Integer idLop){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Lop lop = null;
        try {
            String hql="""
                       select l from Lop l left join fetch l.dsSinhVien
                       where l.idLop=:idLop
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idLop", idLop);
            lop = (Lop) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return lop;        
    }
    
    public static Integer taoLop(Lop lop){
        Integer result = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (timLop(lop.getIdLop())!=null){
            return result;
        }
        try{
            session.getTransaction().begin();
            result = (Integer) session.save(lop);
            session.getTransaction().commit();
        }catch(HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return result;
    }
    
    public static boolean xoaLop(Integer idLop){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Lop lop = timLop(idLop);
        if (lop==null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.delete(lop);
            session.getTransaction().commit();
        }catch(HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;        
    }
    public static Integer demSoLuongSV(Integer idLop,String gioiTinh){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer result =0;
        String hql=null;
        try {
            if (gioiTinh==null){
                hql="""
                    select count(*)
                    from Lop l left join SinhVien sv on l.idLop=sv.lop
                    where l.idLop=:idLop
                    """;
            }else{
                hql="""
                    select count(*)
                    from Lop l left join SinhVien sv on l.idLop=sv.lop
                    where l.idLop=:idLop and sv.gioiTinh=:gioiTinh
                    """;            
            }            
            Query query = session.createQuery(hql);
            if (gioiTinh!=null) query.setParameter("gioiTinh", gioiTinh);
            query.setParameter("idLop", idLop);
            result = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;       
    }
}
