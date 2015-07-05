package com.university;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "course",schema = "univ_schema")
public class Course {

	private long id;
	private String name;
	private Set<CourseStudent>courseStudents = new HashSet<CourseStudent>();

	@OneToMany(mappedBy ="course" )
	public Set<CourseStudent> getCourseStudents() {
		return courseStudents;
	}
	public void setCourseStudents(Set<CourseStudent> courseStudents) {
		this.courseStudents = courseStudents;
	}
	public void addCourseStudent(CourseStudent courseStudent){
		this.courseStudents.add(courseStudent);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "course_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
