package com.university;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class StudentDAO {
	
	@Autowired private SessionFactory sessionFactory;
	
	/**
	 * @Transactional annotation below will trigger Spring Hibernate transaction manager to automatically create
	 * a hibernate session. See src/main/webapp/WEB-INF/servlet-context.xml
	 */
	@Transactional
	public List getAllStudents() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Student").list();
	}

	@Transactional
	public List getCourseByStudent(Student student){
		Session session = sessionFactory.getCurrentSession();

		StringBuilder query = new StringBuilder();
		query
				.append("FROM Student s where s.name='")
				.append(student.getName())
				.append("'");
		return session.createQuery(query.toString()).list();

	}

	@Transactional
	public void addStudent(Student student){
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		query
				.append("FROM Student s WHERE s.name='")
				.append(student.getName())
				.append("'AND s.course='")
				.append(student.getCourse())
				.append("'");

		List nameFromDB=session.createQuery(query.toString()).list();
		if(nameFromDB.size()==0)
			session.save(student);
	}


	@Transactional
	public List getStudentsByCourse(Student student){
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		query
				.append("FROM Student s where s.course='")
				.append(student.getCourse())
				.append("'");
		return session.createQuery(query.toString()).list();
	}

}
