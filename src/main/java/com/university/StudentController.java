package com.university;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class StudentController {

	@Autowired private StudentDAO studentDAO;


	private List fillCourses(){
		List<String> courseList = new ArrayList();
		courseList.add("Math");
		courseList.add("Physics");
		courseList.add("Biology");
		return courseList;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> modelMap,@ModelAttribute Student model) {
		Student userForm = new Student();
		modelMap.put("userForm", userForm);

		modelMap.put("courseList", fillCourses());

		modelMap.put("students", studentDAO.getAllStudents());
		modelMap.put("alert", "List of all students and courses");
		return "index";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") Student student,
									  Map<String, Object> modelMap,@RequestParam String action) {
		modelMap.put("courseList", fillCourses());
		if(student.getName().equals("")) {
			modelMap.put("students", studentDAO.getAllStudents());
			modelMap.put("alert", "List of all students and courses");
			modelMap.put("alert_error", "not correct input");
			return "index";
		}

		if(action.equals("Register student for course")){
			studentDAO.addStudent(student);
			modelMap.put("students", studentDAO.getAllStudents());
			modelMap.put("alert","List of all students and courses");

		}
		else if(action.equals("Show all students on course")){
			modelMap.put("students", studentDAO.getStudentsByCourse(student));
			modelMap.put("alert","List of all students on course");

		}
		else if (action.equals("Show all courses of student")){
			modelMap.put("students", studentDAO.getCourseByStudent(student));
			modelMap.put("alert","List of all courses of student");

		}

		return "index";
	}


}
