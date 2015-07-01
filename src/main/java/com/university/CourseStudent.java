package com.university;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pc-users on 7/1/2015.
 */
@Entity
@Table(name = "course_student",schema = "univ_schema")
public class CourseStudent {

    private long id;
    private Student student;
    private Course course;
    private Date date;


    @Id
    @Column(name = "course_student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(name = "reg_date")
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }



}
