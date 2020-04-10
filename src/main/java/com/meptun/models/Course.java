/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

/**
 *
 * @author roony
 */
public class Course {
    private String courseCode;
    private String courseName;
    private CourseType courseType;
    private Teacher courseLecturer;

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
    
    
}
