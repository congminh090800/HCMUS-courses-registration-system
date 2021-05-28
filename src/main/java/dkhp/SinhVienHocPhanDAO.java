/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dkhp;

import constants.Constants;
import hocki.HocKiHienTaiDAO;
import hocphan.HocPhan;
import java.util.List;
import monhoc.MonHoc;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import vaitro.SinhVien;

/**
 *
 * @author Admin
 */
public class SinhVienHocPhanDAO {
    public static boolean daDangKiMon(SinhVien sv, HocPhan hp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer idSv = sv.getIdSv();
        String maMonHoc = hp.getMonHoc().getMaMonHoc();
        Integer idHk = HocKiHienTaiDAO.layThongTinHKHT().getHkht();                
        Integer result = 0;
        try {
            String hql="""
                       select count(distinct hp.monHoc)
                       from SinhVienHocPhan svhp left join SinhVien sv on svhp.sinhVien.idSv=sv.idSv
                       left join HocPhan hp on hp.maHocPhan=svhp.hocPhan.maHocPhan
                       where sv.idSv=:idSv and hp.monHoc.maMonHoc=:maMonHoc and hp.hocKi.idHk=:idHk
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idSv", idSv);
            query.setParameter("maMonHoc", maMonHoc);
            query.setParameter("idHk", idHk);
            result = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result>0;
    }
    
    public static Integer soLuongMon(SinhVien sv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer idSv = sv.getIdSv();
        Integer result = 0;
        Integer idHk = HocKiHienTaiDAO.layThongTinHKHT().getHkht();                
        try {
            String hql="""
                       select count(distinct hp.monHoc)
                       from SinhVienHocPhan svhp left join SinhVien sv on svhp.sinhVien.idSv=sv.idSv
                       left join HocPhan hp on hp.maHocPhan=svhp.hocPhan.maHocPhan
                       where sv.idSv=:idSv and hp.hocKi.idHk=:idHk
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idSv", idSv);
            query.setParameter("idHk", idHk);
            result = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;
    }
    
    public static boolean trungThoiGianHoc(SinhVien sv, HocPhan hp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer idSv = sv.getIdSv();
        String ngayHoc = hp.getNgayHoc();
        Integer caHoc = hp.getCaHoc();
        Integer idHk = HocKiHienTaiDAO.layThongTinHKHT().getHkht();        
        Integer result = 0;
        try {
            String hql="""
                       select count(distinct hp.monHoc)
                       from SinhVienHocPhan svhp left join SinhVien sv on svhp.sinhVien.idSv=sv.idSv
                       left join HocPhan hp on hp.maHocPhan=svhp.hocPhan.maHocPhan
                       where sv.idSv=:idSv and hp.ngayHoc=:ngayHoc and hp.caHoc=:caHoc and hp.hocKi.idHk=:idHk
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idSv", idSv);
            query.setParameter("ngayHoc", ngayHoc);
            query.setParameter("caHoc", caHoc);
            query.setParameter("idHk", idHk);
            result = ((Long)query.uniqueResult()).intValue();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result>0;        
    }
    
    public static boolean dangKiHocPhan(SinhVien sinhVien, HocPhan hocPhan){
        if (soLuongMon(sinhVien)>=Constants.GIOI_HAN_MON || daDangKiMon(sinhVien,hocPhan) || trungThoiGianHoc(sinhVien, hocPhan)){
            return false;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        SinhVienHocPhan svhp = new SinhVienHocPhan(sinhVien, hocPhan);
        try{
            session.getTransaction().begin();
            session.save(svhp);
            session.getTransaction().commit();
        }catch(HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;
    }
    public static SinhVienHocPhan timSVHP(Integer idSvhp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        SinhVienHocPhan svhp = null;
        try {
            String hql="""
                       select svhp from SinhVienHocPhan svhp
                       where svhp.idSvhp=:idSvhp
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idSvhp", idSvhp);
            svhp = (SinhVienHocPhan) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return svhp;        
    }
    public static SinhVienHocPhan timSVHP(Integer idSv, String maHocPhan){
        Session session = HibernateUtil.getSessionFactory().openSession();
        SinhVienHocPhan svhp = null;
        try {
            String hql="""
                       select svhp from SinhVienHocPhan svhp
                       left join SinhVien sv on sv.idSv = svhp.sinhVien.idSv
                       left join HocPhan hp on hp.maHocPhan = svhp.hocPhan.maHocPhan
                       where sv.idSv=:idSv and hp.maHocPhan=:maHocPhan
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idSv", idSv);
            query.setParameter("maHocPhan", maHocPhan);
            svhp = (SinhVienHocPhan) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return svhp;        
    }    
    public static boolean huyDKHP(Integer idSvhp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        SinhVienHocPhan svhp = timSVHP(idSvhp);
        if (svhp==null){
            return false;
        }
        try{
            session.getTransaction().begin();
            session.delete(svhp);
            session.getTransaction().commit();
        }catch(HibernateException ex){
            session.getTransaction().rollback();
            System.err.println(ex);
        }finally{
            session.close();
        }
        return true;        
    }
    
    public static List<SinhVienHocPhan> xemSvTrongHp(HocPhan hocPhan, String keyword){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String maHocPhan = hocPhan.getMaHocPhan();
        List<SinhVienHocPhan> result = null;
        try {
            String hql="""
                       select svhp
                       from SinhVienHocPhan svhp left join SinhVien sv on svhp.sinhVien.idSv=sv.idSv
                       left join HocPhan hp on hp.maHocPhan=svhp.hocPhan.maHocPhan
                       where hp.maHocPhan=:maHocPhan and
                       (sv.mssv like :keyword or sv.hoTen like :keyword or hp.maHocPhan like :keyword)
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("maHocPhan", maHocPhan);
            query.setParameter("keyword", "%"+keyword+"%");
            result =(List<SinhVienHocPhan>)query.getResultList();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;         
    }
    public static List<HocPhan> xemHpCuaSv(SinhVien sinhVien){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer idSv = sinhVien.getIdSv();
        Integer idHk = HocKiHienTaiDAO.layThongTinHKHT().getHkht();
        List<HocPhan> result = null;
        try {
            String hql="""
                       select hp
                       from HocPhan hp left join fetch hp.monHoc left join fetch hp.hocKi, SinhVienHocPhan svhp, SinhVien sv
                       where sv.idSv=:idSv and hp.maHocPhan=svhp.hocPhan.maHocPhan
                       and sv.idSv=svhp.sinhVien.idSv and hp.hocKi.idHk=:idHk
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idSv", idSv);
            query.setParameter("idHk", idHk);
            result =(List<HocPhan>)query.getResultList();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;         
    }
    public static List<MonHoc> xemMonDaDangKi(SinhVien sinhVien){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer idSv = sinhVien.getIdSv();
        Integer idHk = HocKiHienTaiDAO.layThongTinHKHT().getHkht();
        List<MonHoc> result = null;
        try {
            String hql="""
                       select distinct hp.monHoc
                       from SinhVienHocPhan svhp left join SinhVien sv on svhp.sinhVien.idSv=sv.idSv
                       left join HocPhan hp on hp.maHocPhan=svhp.hocPhan.maHocPhan
                       where sv.idSv=:idSv and hp.hocKi.idHk = :idHk
                       """;
            Query query = session.createQuery(hql);
            query.setParameter("idSv", idSv);
            query.setParameter("idHk", idHk);
            result =(List<MonHoc>)query.getResultList();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return result;         
    }
}
