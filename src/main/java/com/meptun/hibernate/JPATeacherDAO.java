/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.Teacher;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Adewuyi Toluwanimi
 */
public class JPATeacherDAO implements  TeacherDAO{
    Transaction transaction;
    Session session;

    public JPATeacherDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    @Override
    public void saveTeacher(Teacher t) {
        try {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    
    @Override
    public void deleteTeacher(Teacher t) {try {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void updateTeacher(Teacher t) {try {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Teacher> listTeachers() {
        String hql = "FROM com.meptun.models.Teacher";
        Query query = session.createQuery(hql);
        return query.list();
    }
    
    @Override
    public void close(){
        session.close();
    }   
}
