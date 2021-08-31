package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;

public interface CourseDAO {

    List<Course> getAllCourses();

    Course getCourseById(Integer cId);

}
