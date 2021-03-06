/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hocki;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

/**
 *
 * @author Admin
 */
public class HocKiHienTaiDAO {
    public static HocKiHienTai layThongTinHKHT(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocKiHienTai hkht=null;
        try {
            String hql="""
                       select hkht from HocKiHienTai hkht left join fetch hkht.hocki
                       """;
            Query query = session.createQuery(hql);
            hkht = (HocKiHienTai) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return hkht;  
    }
    public static void thayDoiHKHT(HocKi hocKi){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer idHk = hocKi.getIdHk();
        Integer idHt = layThongTinHKHT().getHkht();
        try{
            session.getTransaction().begin();
            String hql = "UPDATE HocKiHienTai set hkht= :idHk "  + 
                         "WHERE id = :idHt";
            Query query = session.createQuery(hql);
            query.setParameter("idHk", idHk);
            query.setParameter("idHt", idHt);  
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
    }
}
