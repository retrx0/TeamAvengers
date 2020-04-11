/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.Student;
import java.util.List;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
public interface StudentDAO extends AutoCloseable{
    public void saveStudent(Student s);
    public void deleteStudent(Student s);
    public void updateStudent(Student s);
    public List<Student> listStudents();
    
    default public void close(){};
}
