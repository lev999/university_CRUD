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
	public Set<Course> getCoursesByStudent(long studentId) {
		Session session = sessionFactory.getCurrentSession();

		String str="SELECT course_student_id FROM Course_student s WHERE s.student_id=:studentId";
		Query query=session.createQuery(str);
		query.setParameter("studentId", studentId);
		List<Integer> entityId = query.list();

		Set<Course> courses = new HashSet<Course>();
		for (Integer id_course : entityId) {
			System.out.println("ID========="+id_course);
			courses.add((Course) session.get(Course.class, id_course));
		}
		return courses;
	}
	@Transactional
	public boolean isCourseAndStudentInDB(Course course, Student student) {
		Session session = sessionFactory.getCurrentSession();

		String str="SELECT id FROM Course_student s WHERE s.course_id=:courseId AND s.student_id=:studentId";
		Query query=session.createQuery(str);
		query.setParameter("courseId", course.getId());
		query.setParameter("studentId", student.getId());

		try{
			Long entityId = (Long)query.list().get(0);
			return false;
		}catch (IndexOutOfBoundsException e){
			return true;
		}
	}
}
