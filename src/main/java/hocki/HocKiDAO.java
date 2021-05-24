/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hocki;

import java.util.List;
import java.util.Objects;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

/**
 *
 * @author Admin
 */
public class HocKiDAO {
    public static HocKi timHocKi(Integer idHk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocKi hocKi = null;
        try {
            String hql="""
                       select hk from HocKi hk
                       where hk.idHk=:idHk
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idHk", idHk);
            hocKi = (HocKi) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return hocKi;        
    }
    
    public static boolean taoHocKi(HocKi hocKi){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (timHocKi(hocKi.getIdHk())!=null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.save(hocKi);
            session.getTransaction().commit();
        }catch(HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;
    }
    
    public static boolean xoaHocKi(Integer idHk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocKi hk = timHocKi(idHk);
        if (hk == null || Objects.equals(HocKiHienTaiDAO.layThongTinHKHT().getHkht(), hk.getIdHk())){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.delete(hk);
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }
        return true;        
    }
    
    public static List<HocKi> dsHocKi(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<HocKi> dsHocKi = null;
        try {
            String hql ="select hk from HocKi hk left join fetch hk.hocKiHienTai";
            Query query = session.createQuery(hql);
            dsHocKi = query.getResultList();      
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dsHocKi;        
    }
}
