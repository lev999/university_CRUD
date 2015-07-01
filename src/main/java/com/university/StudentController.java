package com.university;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class StudentController {

	@Autowired private Student3DAO student3DAO;
	@Autowired private CourseDAO courseDAO;
	@Autowired private StudentDAO studentDAO;
	@Autowired private CourseStudentDAO courseStudentDAO;

	private static final String STUDENTS_BY_COURSE ="Show all students on course";
	private static final String COURSES_BY_STUDENT ="Show all courses of student";
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

		modelMap.put("students", student3DAO.getAllStudents());
		modelMap.put("alert", "List of all students and courses");
		return "index";
	}


	@Transactional
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userFormData") UserFormData userFormData,
									  Map<String, Object> modelMap,@RequestParam String action) {
		modelMap.put("courseList", fillCourses());

		Student student= studentDAO.getStudent(userFormData.getStudentName());
		Course course = courseDAO.getCourse(userFormData.getCourseName());

		if (course == null) {
			course = new Course();
			course.setName(userFormData.getCourseName());
		}
		if (student == null) {
			student = new Student();
			student.setName(userFormData.getStudentName());
		}

		if(student.getName().equals("")
				&&!action.equals(STUDENTS_BY_COURSE)
				||!student.getName().matches( "^[a-zA-Z\\s]*$")) {
			return  "index";
		}

		if(action.equals(STUDENT_REGISTER)) {

			CourseStudent courseStudent = new CourseStudent();
			courseStudent.setDate(new Date());
			courseStudent.setStudent(student);
			courseStudent.setCourse(course);
			courseStudentDAO.updateCourseStudent(courseStudent);
		}
		else if(action.equals(STUDENTS_BY_COURSE)){
			modelMap.put("students", printStudents(course.getCourseStudents()));
		}
		else if(action.equals(COURSES_BY_STUDENT)){
			modelMap.put("students", printCourses(student.getCourseStudents()));
		}

		return "index";
	}

	private ArrayList<String> printStudents(Set<CourseStudent> arrayToIterate){
		ArrayList<String> outputArray = new ArrayList<String>();
		outputArray.add("Student name");
		int i=0;
		for (CourseStudent row : arrayToIterate) {
			i++;
			outputArray.add(row.getStudent().getName());
		}
		return outputArray;
	}
	private ArrayList<String> printCourses(Set<CourseStudent> arrayToIterate){
		ArrayList<String> outputArray = new ArrayList<String>();
		int i=0;
		outputArray.add("Course:Registration date");
		for (CourseStudent row : arrayToIterate) {
			i++;
			outputArray.add( row.getCourse().getName()+":" + row.getDate());
		}
		return outputArray;
	}
}
