package com.university;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hsqldb.lib.HashMappedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class CourseStudentDAO {

	@Autowired private SessionFactory sessionFactory;

	@Transactional
	public void updateCourseStudent(CourseStudent courseStudent) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(courseStudent);
	}

	@Transactional
	public boolean isCourseAndStudentInDB(Course course, Student student) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder queryStr=new StringBuilder();
		queryStr
				.append("SELECT id FROM CourseStudent s WHERE s.course=")
				.append(course.getId())
				.append(" AND s.student=")
				.append(student.getId());
		Query query=session.createQuery(queryStr.toString());

		try{
			Long entityId = (Long)query.list().get(0);
			return true;
		}catch (IndexOutOfBoundsException e){
			return false;
		}
	}


}
