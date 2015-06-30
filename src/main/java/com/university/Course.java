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


}
