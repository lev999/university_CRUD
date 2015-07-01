package com.university;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;


@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class Student2DAO {

	@Autowired private SessionFactory sessionFactory;

	@Transactional
	public void updateStudent(Student2 student2) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(student2);
	}

	@Transactional
	public Student2 getStudent(String name) {
		Session session = sessionFactory.getCurrentSession();

		String str="SELECT id FROM Student2 s WHERE s.name=:name";
		Query query=session.createQuery(str);
		query.setParameter("name", name);

		try{
			Long entityId = (Long)query.list().get(0);
			return (Student2)session.get(Student2.class,entityId);
		}catch (java.lang.IndexOutOfBoundsException e){
			return null;
		}
	}


}
