package jpa.service;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseServiceTest {

    private static CourseDAO courseDAO = null;

    @BeforeAll
    public static void setUp() {
        System.out.println("@BeforeAll - setUp()");

        courseDAO = new CourseService();
    }

    @Test
    public void testGetAllCourses() {
        System.out.println("---------- ---------- ---------- ---------- ----------");
        System.out.println("@Test - testGetAllCourses()");

        // given
        int expectedNumberOfCourses = 10;

        // when
        List<Course> courseList = courseDAO.getAllCourses();
        int actualNumberOfCourses = courseList.size();

        // then
        assertEquals(expectedNumberOfCourses, actualNumberOfCourses);
    }

    @Test
    public void testGetCourseById() {
        System.out.println("---------- ---------- ---------- ---------- ----------");
        System.out.println("@Test - testGetCourseById()");

        // given
        Integer idIn = 3;
        String expectedCourseName = "Anatomy";

        // when
        Course course = courseDAO.getCourseById(idIn);
        String actualCourseName = course.getName();

        // then
        assertEquals(expectedCourseName, actualCourseName);
    }

}
