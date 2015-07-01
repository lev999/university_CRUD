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
				&&!action.equals(Buttons.STUDENTS_BY_COURSE)
				||!student.getName().matches( "^[a-zA-Z\\s]*$")) {
			return  "index";
		}

		if(action.equals(Buttons.STUDENT_REGISTER.getValue())) {

			CourseStudent courseStudent = new CourseStudent();
			courseStudent.setDate(new Date());
			courseStudent.setStudent(student);
			courseStudent.setCourse(course);
			courseStudentDAO.updateCourseStudent(courseStudent);
			modelMap.put("alert", "Data base updated");
		}

		else if(action.equals(Buttons.STUDENTS_BY_COURSE.getValue())){
			modelMap.put("students", printAllStudents(new ArrayList (course.getCourseStudents()), Buttons.STUDENTS_BY_COURSE));
		}
		else if(action.equals(Buttons.COURSES_BY_STUDENT.getValue())){

			modelMap.put("students", printAllStudents(new ArrayList (course.getCourseStudents()),Buttons.COURSES_BY_STUDENT));
		}
		else if(action.equals(Buttons.SHOW_ALL_COURSES.getValue())){
			modelMap.put("students", printAllStudents(studentDAO.getAllCourses(), Buttons.SHOW_ALL_COURSES));
		}
		else if(action.equals(Buttons.SHOW_ALL_STUDENTS.getValue())){
			System.out.println("DDDD:"+studentDAO.getAllStudents());
			modelMap.put("students", printAllStudents(studentDAO.getAllStudents(),Buttons.SHOW_ALL_STUDENTS));
		}

		return "index";
	}

	private ArrayList<String> printAllStudents(List allEntities,Buttons buttons) {
		ArrayList<String> outputArray = new ArrayList<String>();
		for (Object row : allEntities) {
			outputArray.add(prepareStr(row, buttons));
		}
		return outputArray;

	}
	private String prepareStr(Object row, Buttons buttons){
		StringBuilder stringBuilder = new StringBuilder();

		switch (buttons){
			case SHOW_ALL_STUDENTS:
				return stringBuilder.
						append(((Student) row).getName())
						.toString();

			case SHOW_ALL_COURSES:
				return stringBuilder
						.append(((Course) row).getName())
						.toString();

			case COURSES_BY_STUDENT:
				return stringBuilder
						.append("Course:Registration date")
						.append(((CourseStudent) row).getCourse().getName())
						.append(":")
						.append(((CourseStudent) row).getDate())
						.toString();

			case STUDENTS_BY_COURSE:
				return stringBuilder
						.append("Student name")
						.append(((CourseStudent) row).getStudent().getName())
						.toString();
		}

	return null;
	}


	enum Buttons{
		STUDENTS_BY_COURSE("Show all students on course"),
		COURSES_BY_STUDENT("Show all courses of student"),
		STUDENT_REGISTER("Register student for course"),
		SHOW_ALL_STUDENTS("Show all students"),
		SHOW_ALL_COURSES("Show all courses");

		private String value;
		Buttons(String str) {
			this.value =str;
		}

		public String getValue() {
			return value;
		}

	}
}
