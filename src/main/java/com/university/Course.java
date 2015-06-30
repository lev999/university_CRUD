package com.university;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "course",schema = "univ")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private long id;

	@Column(name = "course_name")
	private String name;

	@ManyToMany(mappedBy = "courses")
	private Set<Student2> students = new HashSet<Student2>();

	@Column(name = "course_date", columnDefinition = "timestamp without time zone")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

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

	public Set<Student2> getStudents() {
		return students;
	}

	public void setStudents(Set<Student2> students) {
		this.students = students;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}