/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dkhp;

import hocki.HocKi;
import hocki.HocKiHienTai;
import hocki.HocKiHienTaiDAO;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

/**
 *
 * @author Admin
 */
public class DKHPDAO {
    public static List<DKHP> layDanhSach(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DKHP> dsKiDKHP = null;
        try {
            String hql ="select dk from DKHP dk left join fetch dk.hocKi";
            Query query = session.createQuery(hql);
            dsKiDKHP = query.getResultList();      
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dsKiDKHP;        
    }
    
    public static DKHP timKiDKHP(Integer idDkhp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        DKHP dk = null;
        try {
            String hql="""
                       select dk from DKHP dk left join fetch dk.hocKi
                       where dk.idDkhp=:idDkhp
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idDkhp", idDkhp);
            dk = (DKHP) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dk;        
    }
    
    public static boolean taoKiDKHP(DKHP dk){
        HocKiHienTai hkht = HocKiHienTaiDAO.layThongTinHKHT();
        if (hkht == null) return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocKi hocKi = hkht.getHocki();
        dk.setHocKi(hocKi);
        try{
            session.getTransaction().begin();
            session.save(dk);
            session.getTransaction().commit();
        }catch(HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;
    }
    
}
