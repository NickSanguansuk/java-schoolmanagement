package jpa.entitymodels;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Student")
@NamedQueries({
        @NamedQuery(name = "getAllStudentsQuery", query = "SELECT s FROM Student s"),
        @NamedQuery(name = "getStudentByEmailQuery", query = "SELECT s FROM Student s WHERE s.sEmail = :email")
})
public class Student {
    // Data
    @Id
    @Column(name = "email", nullable = false)
    private String sEmail;

    @Column(name = "name", nullable = false)
    private String sName;

    @Column(name = "password", nullable = false)
    private String sPass;

    @ManyToMany
    @JoinTable(
            name = "Student_Course",
            joinColumns = @JoinColumn(name = "student_email"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> sCourses;

    // Constructors
    public Student() {
    }

    public Student(String sEmail, String sName, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
    }

    public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    // Getters and Setters
    public String getEmail() {
        return sEmail;
    }

    public void setEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getName() {
        return sName;
    }

    public void setName(String sName) {
        this.sName = sName;
    }

    public String getPass() {
        return sPass;
    }

    public void setPass(String sPass) {
        this.sPass = sPass;
    }

    public List<Course> getCourses() {
        return sCourses;
    }

    public void setCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }

    // Methods
    @Override
    public String toString() {
        return "Student{" +
                "sEmail='" + sEmail + '\'' +
                ", sName='" + sName + '\'' +
                ", sPass='" + sPass + '\'' +
                ", sCourses=" + sCourses +
                '}';
    }

}
