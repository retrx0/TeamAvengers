/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.MeptunAccount;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class JPAMeptunAccountDAO implements MeptunAccountDAO{
    Transaction transaction;
    Session session;

    public JPAMeptunAccountDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void saveMeptunAccount(MeptunAccount ma) {
        try {
            transaction = session.beginTransaction();
            session.save(ma);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteMeptunAccount(MeptunAccount ma) {
        try {
            transaction = session.beginTransaction();
            session.delete(ma);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void updateMeptunAccount(MeptunAccount ma) {
        try {
            transaction = session.beginTransaction();
            session.update(ma);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<MeptunAccount> listMeptunAccount() {
        String hql = "FROM com.meptun.models.MeptunAccount";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public void close() {
        session.close();
    }
    
}
