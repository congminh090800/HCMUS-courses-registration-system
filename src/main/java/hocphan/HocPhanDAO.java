/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hocphan;

import hocki.HocKi;
import hocki.HocKiHienTai;
import hocki.HocKiHienTaiDAO;
import java.util.List;
import monhoc.MonHoc;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

/**
 *
 * @author Admin
 */
public class HocPhanDAO {
    public static List<HocPhan> layDanhSach(String keyword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<HocPhan> dsHocPhan = null;
        HocKiHienTai hkht = HocKiHienTaiDAO.layThongTinHKHT();
        if (hkht==null) return dsHocPhan;
        Integer idHk = hkht.getHocki().getIdHk();
        try {
            String hql ="from HocPhan hp left join fetch hp.monHoc left join fetch hp.hocKi where hp.hocKi.idHk=:idHk "+
                        (keyword.equals("")?"":"""
                                               and (hp.monHoc.tenMonHoc like :keyword or hp.gvLyThuyet like :keyword
                                               or hp.tenPhongHoc like :keyword or hp.monHoc.maMonHoc like :keyword)
                                               """);
            Query query = session.createQuery(hql);
            query.setParameter("idHk", idHk);
            if (!keyword.equals("")){
                query.setParameter("keyword", "%"+keyword+"%");   
            }
            dsHocPhan = query.getResultList();      
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return dsHocPhan;
    }
    
    public static HocPhan timHocPhan(String maHocPhan){
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocPhan hocPhan = null;
        Integer idHk = HocKiHienTaiDAO.layThongTinHKHT().getHocki().getIdHk();
        try {
            String hql="""
                       from HocPhan hp left join fetch hp.monHoc left join fetch hp.hocKi
                       where hp.hocKi.idHk=:idHk and hp.maHocPhan=:maHocPhan
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("maHocPhan", maHocPhan);
            query.setParameter("idHk", idHk);
            hocPhan = (HocPhan) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return hocPhan;        
    }
    
    public static boolean taoHocPhan(HocPhan hocPhan, MonHoc monHoc){
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocKi hocKi = HocKiHienTaiDAO.layThongTinHKHT().getHocki();
        if (hocKi==null){
            return false;
        }
        hocPhan.setHocKi(hocKi);
        hocPhan.setMonHoc(monHoc);
        try{
            session.getTransaction().begin();
            session.save(hocPhan);
            session.getTransaction().commit();
        }catch(HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;
    }
    
    public static boolean xoaHocPhan(String maHocPhan){
        Session session = HibernateUtil.getSessionFactory().openSession();
        HocPhan hocPhan= timHocPhan(maHocPhan);
        if (timHocPhan(hocPhan.getMaHocPhan())==null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.delete(hocPhan);
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
