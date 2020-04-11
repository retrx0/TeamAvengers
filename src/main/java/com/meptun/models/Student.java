/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Column(name = "fullname")
    private String full_name;
    @Column(name = "email")
    private String email;
    @Column(name = "major")
    private String major;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String ID;
    @Column(name = "credits")
    private int credits;
    @Column(name = "DOB")
    private LocalDate birthDate;
    
    @JoinColumn(name = "MeptunAccount")
    @ManyToOne
    MeptunAccount meptunAccount;

    public Student() {
    }

    public Student(String name, String email, String major, int credits, LocalDate birthDate) {
        this.full_name = name;
        this.email = email;
        this.major = major;
        this.credits = credits;
        this.birthDate = birthDate;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public MeptunAccount getMeptunAccount() {
        return meptunAccount;
    }

    public void setMeptunAccount(MeptunAccount meptunAccount) {
        this.meptunAccount = meptunAccount;
    }

    @Override
    public String toString() {
        return "Student{" + "full_name=" + full_name + ", email=" + email + ", major=" + major + ", ID=" + ID + ", credits=" + credits + ", birthDate=" + birthDate + '}';
    }

    
    
}
