package com.university;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "student")
public class Student {
	@Id @GeneratedValue private long id;
	private String name;
	private String course;
	@Column(columnDefinition ="TIMESTAMP")
	private Date date;

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
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

}
