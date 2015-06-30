package com.university;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class CourseDAO {
	
	@Autowired private SessionFactory sessionFactory;
	
	@Transactional
	public void updateCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.save(course);
	}

}
