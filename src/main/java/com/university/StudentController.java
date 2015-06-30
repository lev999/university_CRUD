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


		Course course = new Course();
		course.setDate(new Date());
		course.setName(userFormData.getCourseName());
		Student2 student2 = new Student2();
		student2.setName(userFormData.getStudentName());
		course.getStudents().add(student2);

		courseDAO.updateCourse(course);
		return "index";
	}


}
