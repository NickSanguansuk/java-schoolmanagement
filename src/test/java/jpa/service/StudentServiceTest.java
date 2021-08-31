package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentServiceTest {

    private static StudentDAO studentDAO = null;

    @BeforeAll
    public static void setUp() {
        System.out.println("@BeforeAll - setUp()");

        studentDAO = new StudentService();
    }

    @Test
    public void testGetAllStudents_1() {
        System.out.println("---------- ---------- ---------- ---------- ----------");
        System.out.println("@Test - testGetAllStudents_1()");

        // given
        int expectedNumberOfStudents = 10;

        // when
        List<Student> studentList = studentDAO.getAllStudents();
        int actualNumberOfStudents = studentList.size();

        // then
        assertEquals(expectedNumberOfStudents, actualNumberOfStudents);
    }

    @Test
    public void testGetAllStudents_2() {
        System.out.println("---------- ---------- ---------- ---------- ----------");
        System.out.println("@Test - testGetAllStudents_2()");

        // given
        String expectedThirdStudentEmail = "cstartin3@flickr.com";

        // when
        List<Student> studentList = studentDAO.getAllStudents();
        String actualThirdStudentEmail = studentList.get(2).getEmail();

        // then
        assertEquals(expectedThirdStudentEmail, actualThirdStudentEmail);
    }

    @Test
    public void testGetStudentByEmail() {
        System.out.println("---------- ---------- ---------- ---------- ----------");
        System.out.println("@Test - testGetStudentByEmail()");

        // given
        String emailIn = "hluckham0@google.ru";
        String expectedStudentName = "Hazel Luckham";

        // when
        Student student = studentDAO.getStudentByEmail(emailIn);
        String actualStudentName = student.getName();

        // then
        assertEquals(expectedStudentName, actualStudentName);
    }

    @Test
    public void testValidateStudent() {
        System.out.println("---------- ---------- ---------- ---------- ----------");
        System.out.println("@Test - testValidateStudent()");

        // given
        String emailIn = "hguerre5@deviantart.com";
        String passwordIn = "OzcxzD1PGs";
        //String passwordIn = "OzcxzD1PGsaaa";

        // when
        boolean result = studentDAO.validateStudent(emailIn, passwordIn);

        // then
        assertTrue(result);
    }

    @Test
    public void testRegisterStudentToCourse() {
        System.out.println("---------- ---------- ---------- ---------- ----------");
        System.out.println("@Test - testRegisterStudentToCourse()");

        // given
        String emailIn = "cstartin3@flickr.com";
        Integer cIdIn = 8;

        // when
        studentDAO.registerStudentToCourse(emailIn, cIdIn);
        Student student = studentDAO.getStudentByEmail(emailIn);
        List<Course> courseList = student.getCourses();
        boolean bExpectedTrue = false;
        for (Course c : courseList) {
            if (c.getId() == cIdIn) {
                bExpectedTrue = true;
            }
        }

        // then
        assertTrue(bExpectedTrue);
    }

    @Test
    public void testGetStudentCourses() {
        System.out.println("---------- ---------- ---------- ---------- ----------");
        System.out.println("@Test - testGetStudentCourses()");

        // given
        String emailIn = "cstartin3@flickr.com";
        String expectedCourseName = "Data Structures";

        // when
        List<Course> courseList = studentDAO.getStudentCourses(emailIn);
        String actualCourseName = courseList.get(0).getName();

        // then
        assertEquals(expectedCourseName, actualCourseName);

        for (Course c : courseList) {
            System.out.println(c);
        }
    }


}
