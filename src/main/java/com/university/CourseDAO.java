package com.university;

import org.hibernate.Query;
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
//		session.save(course);
		session.saveOrUpdate(course);
	}
	@Transactional
	public Course getCourse(String name) {
		Session session = sessionFactory.getCurrentSession();

		String str="SELECT id FROM Course c WHERE c.name=:name";
		Query query=session.createQuery(str);
		query.setParameter("name", name);

		try{
			Long entityId = (Long)query.list().get(0);
			return (Course)session.get(Course.class,entityId);
		}catch (java.lang.IndexOutOfBoundsException e){
			return null;
		}
	}
}
