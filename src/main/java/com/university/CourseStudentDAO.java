package com.university;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class CourseStudentDAO {

	@Autowired private SessionFactory sessionFactory;

	@Transactional
	public void updateCourseStudent(CourseStudent courseStudent) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(courseStudent);
	}
}
