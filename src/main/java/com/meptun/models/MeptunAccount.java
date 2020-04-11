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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ABDULRAHMAN ILLO
 */
@Entity
public class MeptunAccount implements Serializable {
    
    @Id
    @Column(name = "meptuncode")
    String meptuncode;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @OneToMany(mappedBy = "meptunAccount")
    private List<Student> students;

    public MeptunAccount() {
    }

    public MeptunAccount(String meptuncode, String username, String passowrd) {
        this.username = username;
        this.password = passowrd;
        this.meptuncode = meptuncode;
    }


    public String getMeptuncode() {
        return meptuncode;
    }

    public void setMeptuncode(String meptuncode) {
        this.meptuncode = meptuncode;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passowrd) {
        this.password = passowrd;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    
}
