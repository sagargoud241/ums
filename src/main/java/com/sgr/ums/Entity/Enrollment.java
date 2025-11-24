package com.sgr.ums.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="enrollment")
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name="Id")
    private Long id;

    // FK to Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // FK to Course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enrolled_date", nullable = false)
    private LocalDate enrolledDate;


    //getter

    public Course getCourse() {
        return course;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }


    //setter


    public void setCourse(Course course) {
        this.course = course;
    }

    public void setEnrolledDate(LocalDate enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Enrollment(){}

    public Enrollment(Course course, LocalDate enrolledDate, Long id, Student student) {
        this.course = course;
        this.enrolledDate = enrolledDate;
        this.id = id;
        this.student = student;
    }


}
