/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.Student;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author ABDULRAHMAN ILLO
 */
public class JPAStudentDAO implements StudentDAO{
    Transaction transaction;
    Session session;
    
    public JPAStudentDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
        
    @Override
    public void saveStudent(Student s) {
        try {
            transaction = session.beginTransaction();
            session.save(s);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public void deleteStudent(Student s) {
         try {
            transaction = session.beginTransaction();
            session.delete(s);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public void updateStudent(Student s) {
        try {
            transaction = session.beginTransaction();
            session.update(s);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Student> listStudents() {
        String hql = "FROM com.meptun.models.Student";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public void close() {
        session.close();
    }
    
}
