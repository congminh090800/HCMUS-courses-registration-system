/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaitro;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
}
