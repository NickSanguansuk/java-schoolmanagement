package jpa.entitymodels;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Course")
@NamedQueries({
        @NamedQuery(name = "getAllCoursesQuery", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "getCourseByIdQuery", query = "SELECT c FROM Course c WHERE c.cId = :id")
})
public class Course {
    // Data
    @Id
    @Column(name = "id", nullable = false)
    private Integer cId;

    @Column(name = "name", nullable = false)
    private String cName;

    @Column(name = "instructor", nullable = false)
    private String cInstructorName;

    //@ManyToMany
    //private List<Student> cStudents;

    // Constructors
    public Course() {
    }

    public Course(Integer cId, String cName, String cInstructorName) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
    }

    // Getters and Setters
    public Integer getId() {
        return cId;
    }

    public void setId(Integer cId) {
        this.cId = cId;
    }

    public String getName() {
        return cName;
    }

    public void setName(String cName) {
        this.cName = cName;
    }

    public String getInstructorName() {
        return cInstructorName;
    }

    public void setInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }

    //public List<Student> getStudents() {
    //    return cStudents;
    //}
    //
    //public void setStudents(List<Student> cStudents) {
    //    this.cStudents = cStudents;
    //}

    // Methods
    @Override
    public String toString() {
        //return "Course{" +
        //        "cId=" + cId +
        //        ", cName='" + cName + '\'' +
        //        ", cInstructorName='" + cInstructorName + '\'' +
        //        '}';
        return String.format("%5s     %-30S%-30s", cId, cName, cInstructorName);
    }


}
