package com.meptun.controller;


    //<editor-fold defaultstate="collapsed" desc="Imports">
import com.sun.javafx.css.StyleManager;
import com.meptun.app.MainApp;
import com.meptun.extras.EmailUtil;
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
import com.meptun.models.Message;
import com.meptun.models.MessageListCell;
import com.meptun.models.Teacher;
import com.sun.javafx.PlatformUtil;
import java.awt.PageAttributes;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
import javafx.util.Callback;
import javafx.util.Pair;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
//</editor-fold>

public class scene1Controller implements Initializable {
    
    //<editor-fold defaultstate="collapsed" desc="FXML Variables">
    @FXML private AnchorPane coursesPane;
    @FXML private AnchorPane containerPane;
    @FXML private AnchorPane sideMenuPane;
    @FXML private AnchorPane topMenuPane;
    @FXML private AnchorPane loginPane;
    @FXML private AnchorPane teachersPane;
    @FXML private AnchorPane messagesPane;
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
    @FXML private Label coursesTakenLabel;
    @FXML private Label creditsTakenLabel;
    @FXML private Label examsTakenLabel;
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
    @FXML private TextArea messageTextArea;
    @FXML private Button forumSendButton;
    @FXML private ListView<Message> messageListView;
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
        CourseDAO cDao = new JPACourseDAO();
        ExamsDAO edao  = new JPAExamsDAO();
        List el = edao.listExams();
        List cl = cDao.listCourses();
        List<Student> l = sDAO.listStudents();
         for(int i=0;i<l.size();i++){
            if(l.get(i).getMeptunAccount().getUsername().equals(usernameField.getText()) && l.get(i).getMeptunAccount().getPassword().equals(passwordField.getText())){
                showNode(upperStackPane, upperHome);
                showNode(menuStackPane, homePane);
                idLabel.setText(l.get(i).getMeptunAccount().getMeptuncode());
                nameLabel.setText(l.get(i).getFull_name());
                majorLabel.setText(l.get(i).getMajor());
                emailLabel.setText(l.get(i).getEmail());
                dateOfBirthLabel.setText(l.get(i).getBirthDate().toString());
                coursesTakenLabel.setText(""+cl.size());
                examsTakenLabel.setText(""+el.size());
                creditsTakenLabel.setText(""+l.get(i).getCredits());
            }else{
                shakeTextField(usernameField);
                shakePasswordField(passwordField);
                loginActionLabel.setText("Incorrect username or password");
            }
        }
    }
    void throwAlert(Alert.AlertType at,String title ,String headertext, String contextText){
        Alert a = new Alert(at);
        a.setTitle(title);
        a.setHeaderText(headertext);
        a.setContentText(contextText);
        a.show();
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
    @FXML void messagesButtonPressed(){
       showNode(menuStackPane, messagesPane);
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
                containerPane.getStylesheets().add("/styles/Style-lightMode.css");
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

    //<editor-fold defaultstate="collapsed" desc="Register Course">
        Course c1 = new Course("IK-IA1", "Web Solutions", CourseType.SEMINAR, 3);
        Course c2 = new Course("IK-IPE", "Ping Pong", CourseType.PE, 0);
        Course c2a = new Course("IK-IPE", "Football", CourseType.PE, 0);
        Course c3 = new Course("IK-IB2", "Signal And Systems", CourseType.SEMINAR, 6);
        Course c4 = new Course("IK-IN1", "Computer Networks", CourseType.SEMINAR, 6);
        Course c5 = new Course("IK-EIS", "Enterprise Information System", CourseType.SEMINAR, 3);
        
   @FXML void registerCoursePressed(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Course Registration");
        dialog.setHeaderText("Select course to register");
        ButtonType register = new ButtonType("Register", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(register, ButtonType.CANCEL);
        dialog.getDialogPane().setPrefSize(500, 200);
        if(containerPane.getStylesheets().get(0).equals("/styles/Style-lightMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-lightMode.css");
        }
        else if(containerPane.getStylesheets().get(0).equals("/styles/Style-darkMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-darkMode.css");
        }
        
        VBox vbox =new VBox();
        vbox.setPrefSize(400, 200);
        vbox.setLayoutX(50);
        vbox.setLayoutY(100);
        
        CourseDAO cdao = new JPACourseDAO();
        
        ObservableList ol = FXCollections.observableArrayList();
        ol.add(c1);ol.add(c2);ol.add(c3);ol.add(c4);ol.add(c2a);ol.add(c5);
        
        List l = cdao.listCourses();
       
        for(int i=0;i<l.size();i++){
            ol.add(l.get(i));
        }
        ComboBox coursesCombo = new ComboBox(ol);
        coursesCombo.getSelectionModel().selectFirst();
        //coursesCombo.setLayoutX(50);
        //coursesCombo.setLayoutY(120);
        coursesCombo.setPrefSize(400, 40);
        vbox.getChildren().add(coursesCombo);
        
        dialog.getDialogPane().setContent(vbox);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == register){ 
                    System.out.println(coursesCombo.getSelectionModel().getSelectedItem().toString());
                    coursesTable.getItems().add((Course)coursesCombo.getSelectionModel().getSelectedItem());
                    cdao.saveCourse((Course)coursesCombo.getSelectionModel().getSelectedItem());
                }
                if(dialogButton == ButtonType.CANCEL){
                    //
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();

    }
   //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="DeRegister Course">
   @FXML void deRegisterCoursePressed(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Course Deregistration");
        dialog.setHeaderText("Select course to deregister");
        
        ButtonType deregister = new ButtonType("Deregister", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deregister, ButtonType.CANCEL);
        dialog.getDialogPane().setPrefSize(500, 200);
        
        if(containerPane.getStylesheets().get(0).equals("/styles/Style-lightMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-lightMode.css");
        }
        else if(containerPane.getStylesheets().get(0).equals("/styles/Style-darkMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-darkMode.css");
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
        coursesCombo.getSelectionModel().selectFirst();
        //coursesCombo.setLayoutX(50);
        //coursesCombo.setLayoutY(120);
        coursesCombo.setPrefSize(400, 40);
        vbox.getChildren().add(coursesCombo);
        
        dialog.getDialogPane().setContent(vbox);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == deregister){
                    ObservableList col = coursesTable.getItems();
                    System.out.println(coursesCombo.getSelectionModel().getSelectedItem().toString());
                    for(int i=0;i<col.size();i++){
                        if(coursesCombo.getSelectionModel().getSelectedItem().toString().equals(col.get(i).toString())){
                            coursesTable.getItems().remove(i);
                            cdao.deleteCourse((Course)coursesCombo.getSelectionModel().getSelectedItem());
                        }
                    }
                    //coursesTable.getItems().remove((Course)coursesCombo.getSelectionModel().getSelectedItem());
                }
                if(dialogButton == ButtonType.CANCEL){
                    //
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
    }
   //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Register Exam">
   @FXML void registerExamPressed(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Exam Registration");
        dialog.setHeaderText("Select exam to register");
        ButtonType register = new ButtonType("Register", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(register, ButtonType.CANCEL);
        dialog.getDialogPane().setPrefSize(500, 200);
        if(containerPane.getStylesheets().get(0).equals("/styles/Style-lightMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-lightMode.css");
        }
        else if(containerPane.getStylesheets().get(0).equals("/styles/Style-darkMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-darkMode.css");
        }
        
        VBox vbox =new VBox();
        vbox.setPrefSize(400, 200);
        vbox.setLayoutX(50);
        vbox.setLayoutY(100);
        
        ExamsDAO edao = new JPAExamsDAO();
        Exams e11 = new Exams("IK-WEBSE", "Ik-205", LocalDate.now(), 25, "Zoltan");
        Exams e22 = new Exams("IK-ESS", "Ik-205", LocalDate.now(), 25, "Toth Laszlo");
        Exams e33 = new Exams("IK-CMNE", "Ik-205", LocalDate.now(), 25, "Gal Zoltan");
        Exams e44 = new Exams("IK-MNGD", "Ik-205", LocalDate.now(), 25, "Gal Zoltan");
        e11.setCourse(c1);
        e22.setCourse(c3);
        e33.setCourse(c4);
        e44.setCourse(c5);
        ObservableList ol = FXCollections.observableArrayList();
        ol.add(e11);ol.add(e22);ol.add(e33);ol.add(e44);
        List l = edao.listExams();
        
        for(int i=0;i<l.size();i++){
            ol.add(l.get(i));
        }
        ComboBox examCombo = new ComboBox(ol);
        examCombo.getSelectionModel().selectFirst();
        //examCombo.setLayoutX(50);
        //examCombo.setLayoutY(120);
        examCombo.setPrefSize(400, 40);
        vbox.getChildren().add(examCombo);
        
        dialog.getDialogPane().setContent(vbox);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == register){ 
                    System.out.println(examCombo.getSelectionModel().getSelectedItem().toString());
                    examTable.getItems().add((Exams)examCombo.getSelectionModel().getSelectedItem());
                    edao.saveExam((Exams)examCombo.getSelectionModel().getSelectedItem());
                }
                if(dialogButton == ButtonType.CANCEL){
                    //
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        
    }  
   //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="DeRegister Exam">
   @FXML void deRegisterExamPressed(){
         Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Exam Deregistration");
        dialog.setHeaderText("Select exam to deregister");
        
        ButtonType deregister = new ButtonType("Deregister", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deregister, ButtonType.CANCEL);
        dialog.getDialogPane().setPrefSize(500, 200);
        
        if(containerPane.getStylesheets().get(0).equals("/styles/Style-lightMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-lightMode.css");
        }
        else if(containerPane.getStylesheets().get(0).equals("/styles/Style-darkMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-darkMode.css");
        }
        VBox vbox =new VBox();
        vbox.setPrefSize(400, 200);
        vbox.setLayoutX(50);
        vbox.setLayoutY(100);
        
        ExamsDAO edao = new JPAExamsDAO();
        ObservableList ol = FXCollections.observableArrayList();
        
        List l = edao.listExams();
        
        for(int i=0;i<l.size();i++){
            ol.add(l.get(i));
        }
        ComboBox examCombo = new ComboBox(ol);
        examCombo.getSelectionModel().selectFirst();
        //examCombo.setLayoutX(50);
        //examCombo.setLayoutY(120);
        examCombo.setPrefSize(400, 40);
        vbox.getChildren().add(examCombo);
        
        dialog.getDialogPane().setContent(vbox);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == deregister){ 
                    ObservableList eol = examTable.getItems();
                    System.out.println(examCombo.getSelectionModel().getSelectedItem().toString());
                    for(int i=0;i<eol.size();i++){
                        if(examCombo.getSelectionModel().getSelectedItem().toString().equals(eol.get(i).toString())){
                            examTable.getItems().remove(i);
                            edao.deleteExams((Exams)examCombo.getSelectionModel().getSelectedItem());
                        }
                    }
                   // examTable.getItems().remove((Exams)examCombo.getSelectionModel().getSelectedItem());
                }
                if(dialogButton == ButtonType.CANCEL){
                    //
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
    }
   //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Message Teacher">
   @FXML void messageTeacherPressed(){
       
       Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Send Email");
        dialog.setHeaderText("Send Email To Teacher");
        ButtonType send = new ButtonType("Send", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(send, ButtonType.CANCEL);
        dialog.getDialogPane().setPrefSize(600, 500);
        if(containerPane.getStylesheets().get(0).equals("/styles/Style-lightMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-lightMode.css");
        }
        else if(containerPane.getStylesheets().get(0).equals("/styles/Style-darkMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-darkMode.css");
        }
        TextArea emailbody = new TextArea();
        emailbody.setPromptText("Body");
        emailbody.setPrefSize(550, 300);
        TextField emailSubject = new TextField();
        emailSubject.setPromptText("Subject");
        emailSubject.setPrefSize(550, 30);
        
        StudentDAO sDAO = new JPAStudentDAO();
        List<Student> l = sDAO.listStudents();
        TextField emailSender = new TextField();
        String getEmail = "";
        for(int i=0;i<l.size();i++){
            if(l.get(i).getMeptunAccount().getUsername().equals(usernameField.getText()) && l.get(i).getMeptunAccount().getPassword().equals(passwordField.getText())){
             getEmail = l.get(i).getEmail();   
            }
        }
        emailSender.setText(getEmail);
        emailSender.setPrefSize(550, 30);
        TextField teachersField = new TextField();
        teachersField.setPrefSize(580, 30);
        if(teachersTable.getSelectionModel().getSelectedItem() != null)
            teachersField.setText(teachersTable.getSelectionModel().getSelectedItem().getEmail());
        VBox vbox = new VBox(teachersField,emailSender,emailSubject,emailbody);
        vbox.setPrefSize(600, 490);
        vbox.setSpacing(5);
        dialog.getDialogPane().setContent(vbox);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == send){ 
                    String smtpHostServer = "smtp.gmail.com";
                    String emailID = teachersField.getText();
	    
	  Properties props = System.getProperties();
	  props.put("mail.smtp.host", smtpHostServer);
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.debug", "false");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.starttls.enable","true");
                    
                    Dialog<String> dialog2 = new Dialog<>();
                        if(containerPane.getStylesheets().get(0).equals("/styles/Style-lightMode.css")){
                            dialog2.getDialogPane().getStylesheets().clear();
                            dialog2.getDialogPane().getStylesheets().add("/styles/Style-lightMode.css");
                        }
                        else if(containerPane.getStylesheets().get(0).equals("/styles/Style-darkMode.css")){
                            dialog2.getDialogPane().getStylesheets().clear();
                            dialog2.getDialogPane().getStylesheets().add("/styles/Style-darkMode.css");
                        }
                    dialog2.setTitle("Email Password");
                    dialog2.setHeaderText("Type Your Email Password");
                    ButtonType done = new ButtonType("Done", ButtonData.OK_DONE);
                    dialog2.getDialogPane().getButtonTypes().addAll(done, ButtonType.CANCEL);
                    dialog2.getDialogPane().setPrefSize(250, 150);
                    PasswordField emailPasswordField = new PasswordField();
                    VBox vb = new VBox(emailPasswordField);
                    vb.setPrefSize(250, 100);
                    dialog2.getDialogPane().setContent(vb);
                    dialog2.setResultConverter(ans -> {
                        if (ans == done){
                            return emailPasswordField.getText();
                        }
                        if(ans == ButtonType.CANCEL){
                            
                        }
                        return null;
                    });
                    Optional<String> res = dialog2.showAndWait();
                    try{
                        if(res.isPresent()){
                            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(emailSender.getText(), res.get());
                                }
                            });
                            session.setDebug(true);
                            if(!teachersField.getText().isEmpty())
                                EmailUtil.sendEmail(session, emailID,emailSubject.getText(), emailbody.getText(),emailSender.getText());
                            }
                            else
                                throwAlert(Alert.AlertType.ERROR, "Error", "Recipient Email Address Invalid", "Invalid recipient address, Please check it and try again");
                        }
                    catch(Exception e){
                        throwAlert(Alert.AlertType.ERROR, "Error", "Could'nt Send Email","Something Went Wrong");
                        e.printStackTrace();
                    }
                    }
                if(dialogButton == ButtonType.CANCEL){
                    //
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
    }
   //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Edit data button">
   @FXML void editDataButtonPressed(){
       Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit data");
        dialog.setHeaderText("Edit your personal data");
        ButtonType done = new ButtonType("Done", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(done, ButtonType.CANCEL);
        
        dialog.getDialogPane().setPrefSize(500, 200);
        if(containerPane.getStylesheets().get(0).equals("/styles/Style-lightMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-lightMode.css");
        }
        else if(containerPane.getStylesheets().get(0).equals("/styles/Style-darkMode.css")){
            dialog.getDialogPane().getStylesheets().clear();
            dialog.getDialogPane().getStylesheets().add("/styles/Style-darkMode.css");
        }
        /*GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));*/
        
        VBox vbox =new VBox();
        vbox.setPrefSize(400, 200);
        vbox.setLayoutX(50);
        vbox.setLayoutY(100);
        
        TextField nameTextField = new TextField();
        TextField emailTextField = new TextField();
        TextField majorTextField = new TextField();
        TextField dobTextField = new TextField();
        
        nameTextField.setEditable(false);
        emailTextField.setEditable(true);
        majorTextField.setEditable(false);
        dobTextField.setEditable(false);
        StudentDAO sdao = new JPAStudentDAO();
        List<Student> l = sdao.listStudents();
        for(int i=0;i<l.size();i++){
            if(l.get(i).getMeptunAccount().getUsername().equals(usernameField.getText())){
                nameTextField.setText(l.get(i).getFull_name());
                emailTextField.setText(l.get(i).getEmail());
                majorTextField.setText(l.get(i).getMajor());
                dobTextField.setText(l.get(i).getBirthDate().toString());
            }
        }
        vbox.getChildren().add(emailTextField);
        /*grid.add(nameTextField, 0, 0);
        grid.add(emailTextField, 0, 1);
        grid.add(majorTextField, 0, 2);
        grid.add(dobTextField, 0, 3);*/
        
        dialog.getDialogPane().setContent(vbox);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == done){ 
                    
                }
                if(dialogButton == ButtonType.CANCEL){
                    for(int i=0;i<l.size();i++){
                        if(l.get(i).getMeptunAccount().getUsername().equals(usernameField.getText())){
                            if(!emailTextField.getText().isEmpty()){
                                l.get(i).setEmail(emailTextField.getText());
                                sdao.updateStudent(l.get(i));
                                emailLabel.setText(emailTextField.getText());
                            }
                            else{
                                throwAlert(Alert.AlertType.ERROR, "Email error","Email texfield is empty", "Email textfield can't be empty");
                            }
                        }
                    }
                }
                return null;
            });
            
        Optional<Pair<String, String>> result = dialog.showAndWait();
   }
   //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Message List View">
   @FXML void messageListViewMouceClicked(){
       messageTextArea.clear();
       messageTextArea.appendText(messageListView.getSelectionModel().getSelectedItem().getMessageBody());
   }
//</editor-fold>
   
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
        containerPane.getStylesheets().add("/styles/Style-lightMode.css");
        containerPane.setPrefWidth(800);
        showNode(upperStackPane, loginPane);
        usernameField.requestFocus();
        showNode(menuStackPane, homePane);
       messageListView.setCellFactory((ListView<Message> listView) -> new MessageListCell());
       Message m1 = new Message("Meptun", "Student", "Soft Dev for Eng", LocalDate.now(), "Well done team Avengers, your project is do far the best, keep it up, you've so far shown that you're really good and you can really work hard.");
       Message m2 = new Message("Meptun", "Student", "Congratulations!", LocalDate.now(), "Hello world, hi. This is a test for messages don't waste your time reading it, well it seems your are already wasting your time reading it. Well good luck then."
       );
       Message m3 = new Message("Kocics Gergely", "Student", "Congratulations!", LocalDate.now(), "Hello world, hi. This is a test for messages don't waste your time reading it, well it seems your are already wasting your time reading it. Well good luck then."
       );
       ObservableList<Message> messageObservableList = FXCollections.observableArrayList();
       messageObservableList.add(m2);
       messageObservableList.add(m1);
       messageObservableList.add(m3);
       messageListView.getItems().addAll(messageObservableList);
       messageTextArea.appendText(messageObservableList.get(0).getMessageBody());
        // TODO
        
    }
    
}