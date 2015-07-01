package com.university;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class StudentDAO {

	@Autowired private SessionFactory sessionFactory;

	@Transactional
	public void updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(student);
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
