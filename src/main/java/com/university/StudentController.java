package com.university;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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


	@Transactional
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userFormData") UserFormData userFormData,
									  Map<String, Object> modelMap,@RequestParam String action) {
		Set<Course> courses;
		modelMap.put("students", studentDAO.getAllStudents());
		modelMap.put("alert", "JUST FOR CHECK DATA! " + userFormData.getCourseName() + "-" + userFormData.getStudentName());
		modelMap.put("courseList", fillCourses());

		Student2 student2=student2DAO.getStudent(userFormData.getStudentName());
		Course course = courseDAO.getCourse(userFormData.getCourseName());


		if (course==null){
			course=new Course();
			course.setName(userFormData.getCourseName());
			course.setDate(new Date());
		}
		if (student2==null){
			student2 = new Student2();
			student2.setName(userFormData.getStudentName());
			student2.setCourses(new HashSet<Course>());
		}

		courses=student2.getCourses();
		courses.add(course);
		student2.setCourses(courses);

		student2DAO.updateStudent(student2);
		return "index";
	}


}
