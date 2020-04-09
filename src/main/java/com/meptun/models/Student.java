/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

import java.time.LocalDate;
import javafx.beans.property.StringProperty;

/**
 *
 * @author student
 */
public class Student {
    private String name;
    private String email;
    private String major;
    private String ID;
    private int credits;
    private LocalDate birthDate;

    public Student(String name, String email, String major, String ID, int credits, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.major = major;
        this.ID = ID;
        this.credits = credits;
        this.birthDate = birthDate;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    
    
}
