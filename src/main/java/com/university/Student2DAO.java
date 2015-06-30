package com.university;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class Student2DAO {
	
	@Autowired private SessionFactory sessionFactory;
	
	@Transactional
	public void updateStudent(Student2 student2) {
		Session session = sessionFactory.getCurrentSession();
		session.save(student2);
	}

}
