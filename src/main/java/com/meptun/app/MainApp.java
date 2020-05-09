package com.meptun.app;

import com.meptun.models.Model;
import com.meptun.controller.scene1Controller;
import com.meptun.hibernate.CourseDAO;
import com.meptun.hibernate.ExamsDAO;
import com.meptun.models.MeptunAccount;
import com.meptun.models.Student;
import com.meptun.hibernate.HibernateUtil;
import com.meptun.hibernate.JPACourseDAO;
import com.meptun.hibernate.JPAExamsDAO;
import com.meptun.hibernate.JPAMeptunAccountDAO;
import com.meptun.hibernate.JPAStudentDAO;
import com.meptun.hibernate.JPATeacherDAO;
import com.meptun.hibernate.MeptunAccountDAO;
import com.meptun.hibernate.StudentDAO;
import com.meptun.hibernate.TeacherDAO;
import com.meptun.models.Course;
import com.meptun.models.CourseType;
import com.meptun.models.Exams;
import com.meptun.models.Teacher;
import java.time.LocalDate;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLTeamAvengersProjectScene1.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Meptun");
        stage.setScene(scene);
        
        stage.show();
    }
    
    public static void main(String[] args) {
       
        //Data Access Objects
        StudentDAO sDAO = new JPAStudentDAO();
        TeacherDAO tDAO = new JPATeacherDAO();
        ExamsDAO eDAO = new JPAExamsDAO();
        CourseDAO cDAO = new JPACourseDAO();
        MeptunAccountDAO maDAO = new JPAMeptunAccountDAO();
        //Students Objects
        Student me = new Student("PCGBP2","Abdulrahman Iliyasu", "illoabdulrahmansoftdev2020@gmail.com", "Computer Science Eng", 30, LocalDate.now());
        Student s2 = new  Student("CR97JH","Veer", "veersinghsoftdev2020@gmail.com", "Computer Science Eng", 35, LocalDate.now());
        Student s3 = new Student("GHVSL8","Tolu", "adewuyitoluwaminisoftdev2020@gmail.com", "Computer Science Eng", 30, LocalDate.now());
        Student s4 = new Student("TYFVG5","Mav", "mafarangonidzashesoftdev2020@gmail.com", "Computer Science Eng",30, LocalDate.now());
        //Meptun Accounts
        MeptunAccount m = new MeptunAccount("PCGBP2","retro", "password");
        MeptunAccount m2 = new MeptunAccount("CR97JH", "veer", "password");
        MeptunAccount m3 = new MeptunAccount("GHVSL8", "tolu", "tolu1");
        MeptunAccount m4 = new MeptunAccount("TYFVG5", "mav", "mav1");
        //Teacher Objects
        Teacher kocsis = new Teacher("IK-KG","Kocsis Gergely", "Soft Dev for Eng", "kocicsg@gmail.com");
        Teacher imrev = new Teacher("Ik-IM","Imre Varga", "System Programming", "imreVarga@gmail.com");
        Teacher tothl = new Teacher("IK-TL","Toth laszlo", "Control Systems", "tothl@gmail.com");
        //saving teachers to DB
        tDAO.saveTeacher(kocsis);
        tDAO.saveTeacher(imrev);
        tDAO.saveTeacher(tothl);
        //Course Objects
        Course sdfe = new Course( "IK-SDFE","Soft-Dev for Eng", CourseType.SEMINAR,3);
        Course cs = new Course("IK-CSFE", "Control Systems", CourseType.SEMINAR,6);
        Course sp = new Course("IK-SPFE","System Programming", CourseType.SEMINAR,3);
        //saving courses to DB
        cDAO.saveCourse(sdfe);
        cDAO.saveCourse(cs);
        cDAO.saveCourse(sp);
        //Exams
        Exams e1 =  new Exams("EX-SDFE","IK-201", LocalDate.now(), 20,"Kocsis Gergely");
        Exams e2 =  new Exams("EX-CSFE","IK-205", LocalDate.now(), 20,"Imre Varga");
        Exams e3 =  new Exams("EX-SPFE","IK-206", LocalDate.now(), 20,"Toth Laszlo");
        //saving exams to DB
        eDAO.saveExam(e1);
        eDAO.saveExam(e2);
        eDAO.saveExam(e3);
        //saving meptun accounts to DB
        maDAO.saveMeptunAccount(m);
        maDAO.saveMeptunAccount(m2);
        maDAO.saveMeptunAccount(m3);
        maDAO.saveMeptunAccount(m4);
        //setting course lecturers
        sdfe.setCourseLecturer(kocsis);
        cs.setCourseLecturer(tothl);
        sp.setCourseLecturer(imrev);
        //setting exam courses
        e1.setCourse(sdfe);
        e2.setCourse(cs);
        e3.setCourse(sp);
        //saving exam and courses again to reflect change
        eDAO.saveExam(e1);
        cDAO.saveCourse(sdfe);
        eDAO.saveExam(e2);
        cDAO.saveCourse(cs);
        eDAO.saveExam(e3);
        cDAO.saveCourse(sp);
        //setting meptun account to students
        me.setMeptunAccount(m);
        s2.setMeptunAccount(m2);
        s3.setMeptunAccount(m3);
        s4.setMeptunAccount(m4);
        //saving students
        sDAO.saveStudent(me);
        sDAO.saveStudent(s2);
        sDAO.saveStudent(s3);
        sDAO.saveStudent(s4);
        List<Student> l = sDAO.listStudents();
        System.out.println("list "+ l);
        
        launch(args); 
    }
}
