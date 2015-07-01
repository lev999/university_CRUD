package com.university;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "student",schema = "univ_schema")
public class Student {

	private long id;
	private String name;
	private Set<CourseStudent>courseStudents = new HashSet<CourseStudent>();

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "student_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "student")
	public Set<CourseStudent> getCourseStudents() {
		return courseStudents;
	}
	public void setCourseStudents(Set<CourseStudent> courseStudents) {
		this.courseStudents = courseStudents;
	}
}
