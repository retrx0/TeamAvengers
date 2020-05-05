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
import java.util.concurrent.CountDownLatch;
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

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Data Access Objects
        StudentDAO sDAO = new JPAStudentDAO();
        TeacherDAO tDAO = new JPATeacherDAO();
        ExamsDAO eDAO = new JPAExamsDAO();
        CourseDAO cDAO = new JPACourseDAO();
        MeptunAccountDAO maDAO = new JPAMeptunAccountDAO();
        //Students Objects
        Student me = new Student("PCGBP2","Abdulrahman Iliyasu", "illoabdulrahmansoftdev2020@gmail.com", "Computer Science Eng", 30, LocalDate.now());
        Student s2 = new Student("CR97JH","Veer Singh", "veersinghsoftdev2020@gmail.com", "Computer Science Eng", 35, LocalDate.now());
        Student s3 = new Student("GHVSL8","Toluwanimi Adewuyi", "adewuyitoluwaminisoftdev2020@gmail.com", "Computer Science Eng", 30, LocalDate.now());
        Student s4 = new Student("TYFVG5","Mav", "mafarangonidzashesoftdev2020@gmail.com", "Computer Science Eng",30, LocalDate.now());
        Student s5 = new Student("PGVGY2", "Richard Bolton", "RichardMacqua@gmail.com", "Business Studies", 25, LocalDate.now());
        Student s6 = new Student("QPRWX4", "Bolaji Adefarati", "Bolajifresh@gmail.com", "Music", 10, LocalDate.now());
        Student s7 = new Student("QWERT2", "Temidire Zoltan", "Tems@gmail.com", "Architectural Science", 30, LocalDate.now());
        Student s8 = new Student("RQYUV7", "Dina Hadad", "Dimye@gmail.com", "Medicine", 35, LocalDate.now());
        Student s9 = new Student("VCWEY8", "Abdallah Mazak", "Abdall@gmail.com", "Chemical Eng", 25, LocalDate.now());
        //Meptun Accounts
        MeptunAccount m =  new MeptunAccount("PCGBP2","retro", "password");
        MeptunAccount m2 = new MeptunAccount("CR97JH", "veer", "password");
        MeptunAccount m3 = new MeptunAccount("GHVSL8", "tolu", "tolu1");
        MeptunAccount m4 = new MeptunAccount("TYFVG5", "mav", "mav1");
        MeptunAccount m5 = new MeptunAccount("PGVGY2", "rich", "rich1");
        MeptunAccount m6 = new MeptunAccount("QPRWX4", "boj", "boj2");
        MeptunAccount m7 = new MeptunAccount("QWERT2", "tems", "tem1");
        MeptunAccount m8 = new MeptunAccount("RQYUV7", "Dina", "Dina1");
        MeptunAccount m9 = new MeptunAccount("VCWEY8", "abs", "abs12");
         //Teacher Objects
        Teacher kocsis = new Teacher("IK-KG","Kocsis Gergely", "Soft Dev for Eng", "kocicsg@gmail.com");
        Teacher imrev =  new Teacher ("Ik-IM","Imre Varga", "System Programming", "imreVarga@gmail.com");
        Teacher tothl =  new Teacher ("IK-TL","Toth laszlo", "Control Systems", "tothl@gmail.com");
        Teacher dol2  =  new Teacher  ("IK-MU", "Donald Lagrer", "Music", "dol2@gmail.com");
        Teacher rom1  =  new Teacher  ("IK-TO", "Roaman Mark", "Business Studies", "rom1@gmail.com");
        Teacher wer1  =  new Teacher  ("IK-QE", "Werzak Rel", "Anatomy", "wer1@gmail.com");
        //saving teachers to DB
        tDAO.saveTeacher(kocsis);
        tDAO.saveTeacher(imrev);
        tDAO.saveTeacher(tothl);
        tDAO.saveTeacher(wer1);
        tDAO.saveTeacher(rom1);
        tDAO.saveTeacher(dol2);
        //Course Objects
        Course sdfe = new Course( "IK-SDFE","Soft-Dev for Eng", CourseType.SEMINAR,3);
        Course cs = new Course("IK-CSFE", "Control Systems", CourseType.SEMINAR,6);
        Course sp = new Course("IK-SPFE","System Programming", CourseType.SEMINAR,3);
        Course mu = new Course("IK-MUSI", "Music", CourseType.LECTURE, 2);
        Course bu = new Course("IK-BUT", "Buisness Studies", CourseType.LECTURE, 5);
        Course ay = new Course("IK-AN", "Anatomy", CourseType.LECTURE, 6);
        //saving courses to DB
        cDAO.saveCourse(sdfe);
        cDAO.saveCourse(cs);
        cDAO.saveCourse(sp);
        cDAO.saveCourse(ay);
        cDAO.saveCourse(mu);
        cDAO.saveCourse(bu);
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
