package com.university;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "student2",schema = "univ")
public class Student2 {

	@Id
	@Column(name = "student2_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "student2_name")
	private String name;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="course_student2",
			joinColumns={@JoinColumn(name="student2_id")},
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
