package com.mirosimo.car_showroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mirosimo.car_showroom.model.Employee;
import com.mirosimo.car_showroom.model.Course;
import com.mirosimo.car_showroom.service.EmployeeService;
import com.mirosimo.car_showroom.service.TrainingCourseService;

@Controller
@RequestMapping("/post-courses")
public class TrainingCourseController {
	
	@Autowired
	TrainingCourseService courseService;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/training-course")
	public String newCustomerForm(Model model) {		
		model.addAttribute("listTrainingCourse", courseService.getAllTrainingCourses());		
		return "courses";
	} 
	
	/*@PutMapping("/{courseId}/employees/{employeeId}")
	Course assignEmployeeToCourse(@PathVariable Long employeeId, @PathVariable Long courseId) {
		Employee emp = employeeService.getEmployeeById(employeeId);
		Course course = courseService.getCourseById(courseId);
		//System.out.println("Course name: " + course.getCourseName());
		course.assignEmployee(emp);
		return courseService.saveCourse(course);
	}*/
	
}
