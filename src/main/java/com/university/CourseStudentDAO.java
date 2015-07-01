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
	@Transactional
	public List<Student> getAllStudents(){
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Student.class).list();
	}
	@Transactional
	public List<Course> getAllCourses(){
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Course.class).list();
	}

	@Transactional
	public Course getCourse(String name) {
		Session session = sessionFactory.getCurrentSession();

		String str="SELECT id FROM Course s WHERE s.name=:name";
		Query query=session.createQuery(str);
		query.setParameter("name", name);

		try{
			Long entityId = (Long)query.list().get(0);
			return (Course)session.get(Course.class,entityId);
		}catch (IndexOutOfBoundsException e){
			return null;
		}
	}

	@Transactional
	public Student getStudent(String name) {
		Session session = sessionFactory.getCurrentSession();

		String str="SELECT id FROM Student s WHERE s.name=:name";
		Query query=session.createQuery(str);
		query.setParameter("name", name);

		try{
			Long entityId = (Long)query.list().get(0);
			return (Student)session.get(Student.class,entityId);
		}catch (IndexOutOfBoundsException e){
			return null;
		}
	}


}
