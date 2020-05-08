/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.hibernate;

import com.meptun.models.Exams;
import java.util.List;

/**
 *
 * @author ngoni
 */
public interface ExamsDAO extends AutoCloseable{
    public void saveExam(Exams e);
    public void deleteExams(Exams e);
    public void updateExams(Exams e);
    public List<Exams> listExams();
    
    default public void close(){};
}
