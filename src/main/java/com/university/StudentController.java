package com.university;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class StudentController {

	@Autowired private StudentDAO studentDAO;
	@Autowired private CourseDAO courseDAO;
	@Autowired private Student2DAO student2DAO;

	private static final String STUDENT_BY_COURSE="Show all students on course";
	private static final String COURSE_BY_STUDENT="Show all courses of student";
	private static final String STUDENT_REGISTER="Register student for course";

	private List fillCourses(){
		List<String> courseList = new ArrayList();
		courseList.add("Math");
		courseList.add("Physics");
		courseList.add("Biology");
		return courseList;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> modelMap) {
		UserFormData userFormData = new UserFormData();
		modelMap.put("userFormData", userFormData);

		modelMap.put("courseList", fillCourses());

		modelMap.put("students", studentDAO.getAllStudents());
		modelMap.put("alert", "List of all students and courses");
		return "index";
	}


	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userFormData") UserFormData userFormData,
									  Map<String, Object> modelMap,@RequestParam String action) {

		modelMap.put("students", studentDAO.getAllStudents());
		modelMap.put("alert", "JUST FOR CHECK DATA! " + userFormData.getCourseName() + "-" + userFormData.getStudentName());

//
//		Course course = new Course();
//		course.setDate(new Date());
//		course.setName(userFormData.getCourseName());
//		Student2 student2 = new Student2();
//		student2.setName(userFormData.getStudentName());
//		course.getStudents().add(student2);


//		Meeting meeting1 = new Meeting("Quaterly Sales meeting");
//		Meeting meeting2 = new Meeting("Weekly Status meeting");
//
//		Employee employee1 = new Employee("Sergey", "Brin");
//		Employee employee2 = new Employee("Larry", "Page");
//
//		employee1.getMeetings().add(meeting1);
//		employee1.getMeetings().add(meeting2);
//		employee2.getMeetings().add(meeting1);
//
//		session.save(employee1);
//		session.save(employee2);

		Student2 student2a=new Student2();
		Student2 student2b=new Student2();

		student2a.setName("bbb");
		student2b.setName("aaa");
		Course courseA = new Course();
		Course courseB = new Course();

		courseA.setName("course A");
		courseB.setName("course B");
		courseA.setDate(new Date());
		courseB.setDate(new Date());

		student2a.getCourses().add(courseA);
		student2a.getCourses().add(courseB);
		student2b.getCourses().add(courseA);

		student2DAO.updateStudent(student2a);
		student2DAO.updateStudent(student2b);
		return "index";
	}


}
