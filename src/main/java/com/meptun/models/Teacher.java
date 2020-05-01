/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Generated;

/**
 *
 * @author roony
 */
@Entity
@Table(name = "Teachers")
public class Teacher implements Serializable{
    @Id
    @Column(name = "id")
    String id;
    @Column(name = "name")
    private String name;
    @Column(name = "SubjectName")
    private String subjectName;
    @Column(name = "email")
    private String email;

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Teacher(String id,String name, String subjectName, String email) {
        this.id = id;
        this.name = name;
        this.subjectName = subjectName;
        this.email = email;
    }
    
}
