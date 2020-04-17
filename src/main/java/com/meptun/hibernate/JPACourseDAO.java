/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.Course;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author veersingh
 */
public class JPACourseDAO implements CourseDAO {
    Transaction transaction;
    Session session;

    public JPACourseDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    

    @Override
    public void saveCourse(Course c) {
        try {
            transaction = session.beginTransaction();
            session.save(c);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteCourse(Course c) {
        try {
            transaction = session.beginTransaction();
            session.delete(c);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void updateCourse(Course c) {
        try {
            transaction = session.beginTransaction();
            session.update(c);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Course> listCourses() {
        String hql = "FROM com.meptun.models.Course";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public void close() {
        session.close();
    }
//branch addition
}
