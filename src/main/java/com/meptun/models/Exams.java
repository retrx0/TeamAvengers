/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Generated;

/**
 *
 * @author ngoni
 */
@Entity
@Table(name = "Exams")
public class Exams implements Serializable{
    int id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course")
    private Course course;
    @Column(name = "dateOfExam")
    private LocalDate dateOfExam;
    @Column(name = "headCount")
    private int headCount;
    @Column(name = "room")
    private String room;
   
    
    public Exams(Course course, LocalDate dateOfExam, int headCount, String room) {
        this.course = course;
        this.dateOfExam = dateOfExam;
        this.headCount = headCount;
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(LocalDate dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }
}
