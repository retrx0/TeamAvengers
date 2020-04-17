/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meptun.controller;

import com.sun.javafx.css.StyleManager;
import com.meptun.app.MainApp;
import com.meptun.extras.ShakeTransition;
import com.meptun.hibernate.CourseDAO;
import com.meptun.hibernate.ExamsDAO;
import com.meptun.hibernate.JPACourseDAO;
import com.meptun.hibernate.JPAExamsDAO;
import com.meptun.models.MeptunAccount;
import com.meptun.models.Model;
import com.meptun.models.Student;
import com.meptun.hibernate.JPAStudentDAO;
import com.meptun.hibernate.JPATeacherDAO;
import com.meptun.hibernate.StudentDAO;
import com.meptun.hibernate.TeacherDAO;
import com.meptun.models.Course;
import com.meptun.models.CourseType;
import com.meptun.models.Exams;
import com.meptun.models.Teacher;
import java.awt.PageAttributes;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author student
 */
public class scene1Controller implements Initializable {
    
    //<editor-fold defaultstate="collapsed" desc="FXML Variables">
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
    @FXML private Label loginActionLabel;
    @FXML private Label dateOfBirthLabel;
    @FXML private ToggleButton darkModeToggleButtton;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TableView<Course> coursesTable;
    @FXML private TableColumn<Course, String> courseID;
    @FXML private TableColumn<Course, String> courseName;
    @FXML private TableColumn<Course, Integer> courseCredits;
    @FXML private TableColumn<Course, CourseType> columnCourseType;
    @FXML private TableView<Teacher> teachersTable;
    @FXML private TableColumn<Teacher, String> teacherName;
    @FXML private TableColumn<Teacher, String> teacherCourse;
    @FXML private TableColumn<Teacher, String> teacherEmail;
    @FXML private TableView<Exams> examTable;
    @FXML private TableColumn<Exams, Course> examCourse;
    @FXML private TableColumn<Exams, LocalDate> examDate;
    @FXML private TableColumn<Exams, String> examRoom;
    @FXML private TableColumn<Exams, Integer> examHeadcount;
    @FXML private TableColumn<Exams, String> examSupervisors;
    @FXML private TextArea forumTextArea;
    @FXML private TextField forumTextField;
    @FXML private Button forumSendButton;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    void shakeTextField(TextField tf){
        tf.setStyle("-fx-text-box-border: #ae0c0c");
        ShakeTransition s =new ShakeTransition(tf);
        s.play();
    }
    void shakePasswordField(PasswordField pf){
        pf.setStyle("-fx-text-box-border: #ae0c0c");
        //-fx-focus-color: red ;
        ShakeTransition s =new ShakeTransition(pf);
        s.play();
    }
    void sendForumMessage(){
        forumTextArea.appendText("@"+usernameField.getText()+": "+forumTextField.getText()+"\n");
        forumTextField.clear();
    }
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
            }else{
                shakeTextField(usernameField);
                shakePasswordField(passwordField);
                loginActionLabel.setText("Incorrect username or password");
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menu Buttons">
    @FXML void coursesButtonPressed() {
        CourseDAO cdao = new JPACourseDAO();
        List<Course> courseList = cdao.listCourses();
        ObservableList<Course> ol = FXCollections.observableArrayList();
        for(int i=0;i<courseList.size();i++){
            ol.add(courseList.get(i));
        }
        courseID.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseCredits.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));
        columnCourseType.setCellValueFactory(new PropertyValueFactory<>("courseType"));
        coursesTable.setItems(ol);
        showNode(menuStackPane, coursesPane);
    }
    @FXML void homeButtonPressed() {
        showNode(menuStackPane, homePane);
    }
    @FXML void teachersButtonPressed() {
        TeacherDAO tdao = new JPATeacherDAO();
        List<Teacher> teacherList = tdao.listTeachers();
        ObservableList<Teacher> ol = FXCollections.observableArrayList();
        for(int i=0;i<teacherList.size();i++){
            ol.add(teacherList.get(i));
        }
        teacherName.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherCourse.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        teacherEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        teachersTable.setItems(ol);
        showNode(menuStackPane, teachersPane);
    }
    @FXML void examButtonPressed(){
        ExamsDAO edao = new JPAExamsDAO();
        List<Exams> examsList = edao.listExams();
        ObservableList<Exams> ol = FXCollections.observableArrayList();
        for(int i=0;i<examsList.size();i++){
            ol.add(examsList.get(i));
        }
        examDate.setCellValueFactory(new PropertyValueFactory<>("dateOfExam"));
        examRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        examCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        examHeadcount.setCellValueFactory(new PropertyValueFactory<>("headCount"));
        examSupervisors.setCellValueFactory(new PropertyValueFactory<>("examSupervisor"));
        examTable.setItems(ol);
        showNode(menuStackPane, examPane);
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
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Login Buttons">
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
        passwordField.setText("");
        loginActionLabel.setText("");
        passwordField.setStyle("-fx-text-box-border: transparent");
        usernameField.setStyle("-fx-text-box-border: transparent");
    }
//</editor-fold>
    
   @FXML void registerCoursePressed(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Register Course");
        dialog.setHeaderText("Select course to register");
        ButtonType register = new ButtonType("Register", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(register, ButtonType.CANCEL);
        
        dialog.getDialogPane().setPrefSize(500, 400);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Label hourLabel = new Label("Hour");
        Label minuteLabel = new Label("Minute");

        grid.add(hourLabel, 0, 0);
        grid.add(minuteLabel, 1, 0);
        
        dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == register){ 
                }
                if(dialogButton == ButtonType.CANCEL){
                    //
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
    }
   @FXML void deRegisterCoursePressed(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Deregister Course");
        dialog.setHeaderText("Select course to deregister");
        ButtonType deregister = new ButtonType("Deregister", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deregister, ButtonType.CANCEL);
        dialog.getDialogPane().setPrefSize(500, 200);
        System.out.println(containerPane.getStylesheets().get(0));
        if(containerPane.getStylesheets().get(0).equals("/styles/Styles.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/dialog-style-light.css");
            dialog.getDialogPane().getStyleClass().clear();
            dialog.getDialogPane().getStyleClass().add("dialog");
        }
        else if(containerPane.getStylesheets().get(0).equals("/styles/Style-darkMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/dialog-style-dark.css");
            dialog.getDialogPane().getStyleClass().clear();
            dialog.getDialogPane().getStyleClass().add("dailog");
        }
        VBox vbox =new VBox();
        vbox.setPrefSize(400, 200);
        vbox.setLayoutX(50);
        vbox.setLayoutY(100);
        
        CourseDAO cdao = new JPACourseDAO();
        ObservableList ol = FXCollections.observableArrayList();
        List l = cdao.listCourses();
        for(int i=0;i<l.size();i++){
            ol.add(l.get(i));
        }
        ComboBox coursesCombo = new ComboBox(ol);
        coursesCombo.setPromptText("Select course to deregister");
        //coursesCombo.setLayoutX(50);
        //coursesCombo.setLayoutY(120);
        coursesCombo.setPrefSize(400, 40);
        vbox.getChildren().add(coursesCombo);
        
        dialog.getDialogPane().setContent(vbox);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == deregister){ 
                    System.out.println(coursesCombo.getSelectionModel().getSelectedItem().toString());
                }
                if(dialogButton == ButtonType.CANCEL){
                    //
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
    }
   @FXML void registerExamPressed(){}
   @FXML void deRegisterExamPressed(){
    }
   @FXML void messageTeacherPressed(){
    }
    
    //<editor-fold defaultstate="collapsed" desc="Forum">
    @FXML void forumButtonPressed(){
        showNode(menuStackPane, forumPane);
    }
    @FXML void forumClearChatButtonPressed() {
        forumTextArea.clear();
    }
    @FXML void forumSendButtonPressed() {
        sendForumMessage();
    }
    @FXML void forumTextFieldKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            sendForumMessage();
        }
    }
//</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        containerPane.getStylesheets().clear();
        containerPane.getStylesheets().add("/styles/Styles.css");
        showNode(upperStackPane, loginPane);
        showNode(menuStackPane, homePane);
        // TODO
    }
    
}
