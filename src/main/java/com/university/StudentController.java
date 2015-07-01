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
		Set<Course> courses;
		modelMap.put("students", student3DAO.getAllStudents());
		modelMap.put("alert", "JUST FOR CHECK DATA! " + userFormData.getCourseName() + "-" + userFormData.getStudentName());
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
		else if(action.equals(COURSES_BY_STUDENT)){

			Set<CourseStudent> coursesSet = course.getCourseStudents();
			Set<CourseStudent> students = student.getCourseStudents();
			String str="Courses return student# :"+coursesSet.size()+" ";
			 str+="Student return  :"+students.size()+" ";

			for (CourseStudent student1 : coursesSet) {
				System.out.println("from course-Student on course:"+student1.getStudent().getName());
				System.out.println("from course-courses on course:"+student1.getCourse().getName());
			}
			for (CourseStudent student1 : students) {
				System.out.println("from student-Student on course:"+student1.getStudent().getName());
				System.out.println("from student-courses on course:"+student1.getCourse().getName());
			}

//			System.out.println();

//			for (CourseStudent courseStudent : courseStudents) {
//				System.out.println("I am here 2");
//
//				if(courseStudent.getStudent().getName().equals(student.getName())){
//					System.out.println("I am here 3");
//					System.out.println("My print:" + courseStudent.getCourse().getName());
//					str+="_"+courseStudent.getCourse().getName();
//				}
//			}
			modelMap.put("alert_error",str);
		}

		return "index";
	}


}
