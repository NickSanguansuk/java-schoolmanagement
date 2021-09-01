package jpa.mainrunner;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {

    private Scanner scanner;
    private StringBuilder sb;

    private CourseService courseService;
    private StudentService studentService;
    private Student currentStudent;

    public SMSRunner() {
        scanner = new Scanner(System.in);
        sb = new StringBuilder();
        courseService = new CourseService();
        studentService = new StudentService();
    }

    public static void main(String[] args) {
        SMSRunner sms = new SMSRunner();

        // ----------
        // For running first time:
        // 1). Create the new schema named `school_mgmt`
        //          DROP DATABASE IF EXISTS `school_mgmt`;
        //          CREATE SCHEMA `school_mgmt`  DEFAULT COLLATE utf8mb4_bin ;
        // 2). Uncomment the line below
        sms.insertDummyRows(); // <--- Comment this line after the first run
        // ----------

        sms.run();
    }

    private void run() {
        // Login or quit
        switch (menu1()) {
            case 1:
                if (studentLogin()) {
                    registerMenu();
                }
                break;
            case 2:
                System.out.println("Goodbye!");
                break;
            default:

        }
    }

    private int menu1() {
        sb.append("\n1. Student Login\n2. Quit Application\nPlease Enter Selection: ");
        System.out.print(sb.toString());
        sb.delete(0, sb.length());

        int choice;

        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: the input is not in the menu choice!");
            choice = 2;
        }

        return choice;
    }

    private boolean studentLogin() {
        boolean retValue = false;
        System.out.print("Enter your email address: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        Student student = studentService.getStudentByEmail(email);
        if (student != null) {
            currentStudent = student;
        } else {
            System.out.println("User Validation failed. GoodBye!");
            return retValue;
        }

        if (currentStudent != null & currentStudent.getPass().equals(password)) {
            List<Course> courses = studentService.getStudentCourses(email);
            System.out.println("My Classes");
            for (Course course : courses) {
                System.out.println(course);
            }
            retValue = true;
        } else {
            System.out.println("User Validation failed. GoodBye!");
        }
        return retValue;
    }

    private void registerMenu() {
        sb.append("\n1. Register a class\n2. Logout\nPlease Enter Selection: ");
        System.out.print(sb.toString());
        sb.delete(0, sb.length());

        int choice;

        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: the input is not in the menu choice!");
            choice = 2;
        }

        switch (choice) {
            case 1:
                List<Course> allCourses = courseService.getAllCourses();
                List<Course> courses = studentService.getStudentCourses(currentStudent.getEmail());
                allCourses.removeAll(courses);
                System.out.printf("%5s     %-30S%-30s\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    System.out.println(course);
                }
                System.out.println();
                System.out.print("Enter Course Number: ");
                int number = scanner.nextInt();
                Course newCourse = courseService.getCourseById(number);

                if (newCourse != null) {
                    studentService.registerStudentToCourse(currentStudent.getEmail(), newCourse.getId());
                    Student temp = studentService.getStudentByEmail(currentStudent.getEmail());

                    //StudentCourseService scService = new StudentCourseService();
                    //List<Course> sCourses = scService.getCoursesByEmail(temp.getEmail());

                    List<Course> sCourses = studentService.getStudentCourses(temp.getEmail());

                    System.out.println("MyClasses");
                    for (Course course : sCourses) {
                        System.out.println(course);
                    }
                }
                System.out.println("You have been signed out. Goodbye!");
                break;
            case 2:
            default:
                System.out.println("You have been signed out. Goodbye!");
        }
    }

    private void insertDummyRows() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SMS");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Testing to see if the data is already in the tables
        StudentDAO studentDAO = new StudentService();
        List<Student> testList = studentDAO.getAllStudents();
        if (testList.size() > 0) {
            System.out.println("---> Tables exist, and already have the dummy data");
            return;
        } else {
            System.out.println("---> Tables don't exist. Creating tables and inserting dummy data");
        }

        // Creating tables and inserting dummy data
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj"));
        studentList.add(new Student("sbowden1@yellowbook.com", "Sonnnie Bowden", "SJc4aWSU"));
        studentList.add(new Student("qllorens2@howstuffworks.com", "Quillan Llorens", "W6rJuxd"));
        studentList.add(new Student("cstartin3@flickr.com", "Clem Startin", "XYHzJ1S"));
        studentList.add(new Student("tattwool4@biglobe.ne.jp", "Thornie Attwool", "Hjt0SoVmuBz"));
        studentList.add(new Student("hguerre5@deviantart.com", "Harcourt Guerre", "OzcxzD1PGs"));
        studentList.add(new Student("htaffley6@columbia.edu", "Holmes Taffley", "xowtOQ"));
        studentList.add(new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j"));
        studentList.add(new Student("ljiroudek8@sitemeter.com", "Laryssa Jiroudek", "bXRoLUP"));
        studentList.add(new Student("cjaulme9@bing.com", "Cahra Jaulme", "FnVklVgC6r6"));

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "English", "Anderea Scamaden"));
        courseList.add(new Course(2, "Mathematics", "Eustace Niemetz"));
        courseList.add(new Course(3, "Anatomy", "Reynolds Pastor"));
        courseList.add(new Course(4, "Organic Chemistry", "Odessa Belcher"));
        courseList.add(new Course(5, "Physics", "Dani Swallow"));
        courseList.add(new Course(6, "Digital Logic", "Glenden Reilingen"));
        courseList.add(new Course(7, "Object Oriented Programming", "Giselle Ardy"));
        courseList.add(new Course(8, "Data Structures", "Carolan Stoller"));
        courseList.add(new Course(9, "Politics", "Carmita De Maine"));
        courseList.add(new Course(10, "Art", "Kingsly Doxsey"));

        entityManager.getTransaction().begin();

        for (Student s : studentList) {
            entityManager.persist(s);
        }

        for (Course c : courseList) {
            entityManager.persist(c);
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
