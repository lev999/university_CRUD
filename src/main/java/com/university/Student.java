package com.university;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "student",schema = "univ_schema")
public class Student {

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "student_name")
	private String name;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="univ_schema.course_student",
			joinColumns={@JoinColumn(name="student_id")},
			inverseJoinColumns={@JoinColumn(name="course_id")})
	private Set<Course> courses = new HashSet<Course>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
