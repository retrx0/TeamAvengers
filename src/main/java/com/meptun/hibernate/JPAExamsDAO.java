/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.Exams;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ngoni
 */
public class JPAExamsDAO implements ExamsDAO{
    
     Transaction transaction;
    Session session;
    
    public JPAExamsDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    

    @Override
    public void saveExam(Exams e) {
         try {
            transaction = session.beginTransaction();
            session.save(e);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    
    @Override
    public List<Exams> listExams() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteExams(Exams e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateExams(Exams e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
