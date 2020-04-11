package com.meptun.app;

import com.meptun.models.Model;
import com.meptun.controller.scene1Controller;
import com.meptun.models.MeptunAccount;
import com.meptun.models.Student;
import com.meptun.hibernate.HibernateUtil;
import com.meptun.hibernate.JPAStudentDAO;
import com.meptun.hibernate.StudentDAO;
import java.time.LocalDate;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLTeamAvengersProjectScene1.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Student MS");
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
        Student me = new Student("Abdulrahman Iliyasu", "abdulrahmaniliyasu86@gmail.com", "Computer Science Eng", 30, LocalDate.now());
        MeptunAccount m = new MeptunAccount("PCGBP2","retro", "password");
        MeptunAccount m2 = new MeptunAccount("CR97JH", "veer", "password");
        MeptunAccount m3 = new MeptunAccount("GHVSL8", "tolu", "tolu1");
        MeptunAccount m4 = new MeptunAccount("TYFVG5", "mav", "mav1");
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        session.save(m);
        session.save(m2);
        session.save(m3);
        session.save(m4);
        session.getTransaction().commit();
        session.close();
        Student s2 = new  Student("Veer", "veer@gmail.com", "Computer Science Eng", 35, LocalDate.now());
        Student s3 = new Student("Tolu", "tolu@gmail.com", "Computer Science Eng", 30, LocalDate.now());
        Student s4 = new Student("Mav", "mav@gmail.com", "Computer Science Eng",30, LocalDate.now());
        try (StudentDAO sDAO = new JPAStudentDAO()){       
            me.setMeptunAccount(m);
            s2.setMeptunAccount(m2);
            s3.setMeptunAccount(m3);
            s4.setMeptunAccount(m4);
            sDAO.saveStudent(me);
            sDAO.saveStudent(s2);
            sDAO.saveStudent(s3);
            sDAO.saveStudent(s4);
            List<Student> l = sDAO.listStudents();
            System.out.println("list "+ l);
        }
        catch(Exception e){}
        launch(args);
    }

}
