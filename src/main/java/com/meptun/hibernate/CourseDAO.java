/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.Course;
import java.util.List;

/**
 *
 * @author veersingh
 */
public interface CourseDAO extends AutoCloseable{
    public void saveCourse(Course c);
    public void deleteCourse(Course c);
    public void updateCourse(Course c);
    public List<Course> listCourses();
    
    default public void close(){};
}
