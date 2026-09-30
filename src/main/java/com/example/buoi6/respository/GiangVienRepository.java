package com.example.buoi6.respository;

import com.example.buoi6.entity.GiangVien;
import com.example.buoi6.until.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GiangVienRepository {
    private Session s;

    public GiangVienRepository() {
        s = HibernateUtil.getFACTORY().openSession();
    }

    public List<GiangVien> getAll() {
        return s.createQuery("FROM GiangVien", GiangVien.class).list();
    }

    public GiangVien getOne(Long id) {
        return s.find(GiangVien.class, id);
    }

    public void add(GiangVien gv) {
        try {
            s.getTransaction().begin();
            s.persist(gv);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(GiangVien gv) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(gv);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Long id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            GiangVien gv = session.get(GiangVien.class, id);
            if (gv != null) {
                session.remove(gv);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<GiangVien> search(String ten, Long tuoiMin, Long tuoiMax) {
        String hql = "FROM GiangVien g WHERE 1=1";
        if (ten != null && !ten.trim().isEmpty()) hql += " AND g.ten LIKE :ten";
        if (tuoiMin != null) hql += " AND g.tuoi >= :tuoiMin";
        if (tuoiMax != null) hql += " AND g.tuoi <= :tuoiMax";

        var q = s.createQuery(hql, GiangVien.class);
        if (ten != null && !ten.trim().isEmpty()) q.setParameter("ten", "%" + ten + "%");
        if (tuoiMin != null) q.setParameter("tuoiMin", tuoiMin);
        if (tuoiMax != null) q.setParameter("tuoiMax", tuoiMax);
        return q.list();
    }

    // ===== TEST CHẠY SQL =====
    public static void main(String[] args) {
        System.out.println(new GiangVienRepository().getAll());
    }
}