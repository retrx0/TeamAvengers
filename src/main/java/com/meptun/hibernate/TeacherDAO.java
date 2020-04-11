/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.Student;
import com.meptun.models.Teacher;
import java.util.List;

/**
 *
 * @author Adewuyi Toluwanimi
 */
public interface TeacherDAO extends AutoCloseable{
    public void saveTeacher(Teacher t);
    public void deleteTeacher(Teacher t);
    public void updateTeacher(Teacher t);
    public List<Teacher> listTeachers();
    
    default public void close(){};
}  

