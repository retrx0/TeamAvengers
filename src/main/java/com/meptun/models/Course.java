/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author roony
 */
@Entity
@Table(name = "Course")
public class Course implements Serializable{
    @Id @Column(name = "courseCode")
    private String courseCode;
    
    @Column(name = "courseName")
    private String courseName;
    
    @Column(name = "courseType")
    private CourseType courseType;
    
    @Column(name = "credits")
    int courseCredits;
    
    @JoinColumn(name = "courseLecturer") @ManyToOne
    private Teacher courseLecturer;
    
    @OneToMany(mappedBy = "course")
    private List<Exams> exams;

    public Course() {
    }

    public Course(String courseCode, String courseName, CourseType courseType) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseType = courseType;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }
    
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Teacher getCourseLecturer() {
        return courseLecturer;
    }

    public void setCourseLecturer(Teacher courseLecturer) {
        this.courseLecturer = courseLecturer;
    }

    public Course(String courseCode, String courseName, CourseType courseType, Teacher courseLecturer) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseLecturer = courseLecturer;
    }

    @Override
    public String toString() {
        return courseName;
    }
    
}
