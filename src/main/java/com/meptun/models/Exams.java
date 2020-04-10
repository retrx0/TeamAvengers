/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

import java.time.LocalDate;

/**
 *
 * @author ngoni
 */
public class Exams {
    private Course course;
    private LocalDate dateOfExam;
    private int headCount;
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
