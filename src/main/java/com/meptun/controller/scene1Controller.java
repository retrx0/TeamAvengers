/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.controller;

import com.sun.javafx.css.StyleManager;
import com.meptun.app.MainApp;
import com.meptun.models.MeptunAccount;
import com.meptun.models.Model;
import com.meptun.models.Student;
import com.meptun.hibernate.JPAStudentDAO;
import com.meptun.hibernate.StudentDAO;
import java.awt.PageAttributes;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author student
 */
public class scene1Controller implements Initializable {
    
    @FXML private AnchorPane coursesPane;
    @FXML private AnchorPane containerPane;
    @FXML private AnchorPane sideMenuPane;
    @FXML private AnchorPane topMenuPane;
    @FXML private AnchorPane loginPane;
    @FXML private AnchorPane teachersPane;
    @FXML private AnchorPane upperHome;
    @FXML private AnchorPane homePane;
    @FXML private AnchorPane forumPane;
    @FXML private AnchorPane examPane;
    @FXML private StackPane menuStackPane;
    @FXML private StackPane upperStackPane;
    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label majorLabel;
    @FXML private Label emailLabel;
    @FXML private Label dateOfBirthLabel;
    @FXML private ToggleButton darkModeToggleButtton;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    
    void showNode(StackPane sp, Node nodeToShow){
       sp.getChildren().clear();
       sp.getChildren().add(nodeToShow);
    }
    void doFadeIn(StackPane stackPane, Node paneToAdd) {
        Node paneToRemove = stackPane.getChildren().get(0);
        stackPane.getChildren().add(paneToAdd);
         FadeTransition ft = new FadeTransition(Duration.millis(300));
        ft.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });
        ft.setNode(paneToAdd);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
    void login(){
        StudentDAO sDAO = new JPAStudentDAO();
         List<Student> l = sDAO.listStudents();
         for(int i=0;i<l.size();i++){
            if(l.get(i).getMeptunAccount().getUsername().equals(usernameField.getText()) && l.get(i).getMeptunAccount().getPassword().equals(passwordField.getText())){
                showNode(upperStackPane, upperHome);
                idLabel.setText(l.get(i).getMeptunAccount().getMeptuncode());
                nameLabel.setText(l.get(i).getFull_name());
                majorLabel.setText(l.get(i).getMajor());
                emailLabel.setText(l.get(i).getEmail());
                dateOfBirthLabel.setText(l.get(i).getBirthDate().toString());
            }
        }
    }

    @FXML void coursesButtonPressed() {
        showNode(menuStackPane, coursesPane);
    }
    @FXML void homeButtonPressed() {
        showNode(menuStackPane, homePane);
    }
    @FXML void teachersButtonPressed() {
        showNode(menuStackPane, teachersPane);
    }
    @FXML void darkModeButtonPressed(){
            if(darkModeToggleButtton.isSelected()){
                containerPane.getStylesheets().clear();
                containerPane.getStylesheets().add("/styles/Style-darkMode.css");
                darkModeToggleButtton.setText("Light Mode");
            }
            else if(!darkModeToggleButtton.isSelected()){
                containerPane.getStylesheets().clear();
                containerPane.getStylesheets().add("/styles/Styles.css");
                darkModeToggleButtton.setText("Dark Mode");
            }
    }
    @FXML void loginKeyPressed(KeyEvent ev){
        if(ev.getCode() == KeyCode.ENTER){
            login();
        }
    }
    @FXML void loginButtonPressed(){
        login();
    }
    @FXML void logoutButtonPressed(){
        showNode(upperStackPane, loginPane);
    }
    @FXML void forumButtonPressed(){
        showNode(menuStackPane, forumPane);
    }
    @FXML void examButtonPressed(){
        showNode(menuStackPane, examPane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showNode(upperStackPane, loginPane);
        showNode(menuStackPane, homePane);
        // TODO
    }
    
}
