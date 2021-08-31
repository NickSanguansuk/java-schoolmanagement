package jpa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;

public class CourseService implements CourseDAO {

    // Data
    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    // Constructors
    public CourseService() {
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
    public List<Course> getAllCourses() {
        this.createEntityManager();

        TypedQuery<Course> query = this.entityManager.createNamedQuery("getAllCoursesQuery", Course.class);
        List<Course> courses = query.getResultList();

        this.closeEntityManager();
        return courses;
    }

    @Override
    public Course getCourseById(Integer cId) {
        this.createEntityManager();

        //TypedQuery<Course> query = this.entityManager.createNamedQuery("getCourseByIdQuery", Course.class);
        //query.setParameter("id", cId);
        //Course course = query.getSingleResult();
        Course course = this.entityManager.find(Course.class, cId);

        this.closeEntityManager();
        return course;
    }


}
