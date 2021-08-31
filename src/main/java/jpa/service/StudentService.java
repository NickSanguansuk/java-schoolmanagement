package jpa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;
import java.util.Objects;

public class StudentService implements StudentDAO {

    // Data
    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    // Constructors
    public StudentService() {
    }

    // Methods
    public void createEntityManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("SMS");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    @Override
    public List<Student> getAllStudents() {
        this.createEntityManager();

        TypedQuery<Student> query = this.entityManager.createNamedQuery("getAllStudentsQuery", Student.class);
        List<Student> students = query.getResultList();

        this.closeEntityManager();
        return students;
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        this.createEntityManager();

        //TypedQuery<Student> query = this.entityManager.createNamedQuery("getStudentByEmailQuery", Student.class);
        //query.setParameter("email", sEmail);
        //Student student = query.getSingleResult();
        Student student = entityManager.find(Student.class, sEmail);

        this.closeEntityManager();
        return student;
    }

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        boolean result;
        Student student = this.getStudentByEmail(sEmail);
        if (student.getPass().equals(sPassword)) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    @Override
    public void registerStudentToCourse(String sEmail, Integer cId) {
        this.createEntityManager();

        Student student = entityManager.find(Student.class, sEmail);

        CourseDAO courseDAO = new CourseService();
        Course course = courseDAO.getCourseById(cId);

        for (Course c : student.getCourses()) {
            if (c.getId() == course.getId()) {
                System.out.println("You are already registered in that course!");
                return; // Return this method
            }
        }

        student.getCourses().add(course);

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();

        closeEntityManager();
    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        Student student = this.getStudentByEmail(sEmail);

        return student.getCourses();
    }


}
