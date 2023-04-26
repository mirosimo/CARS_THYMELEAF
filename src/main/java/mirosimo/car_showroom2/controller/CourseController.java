package mirosimo.car_showroom2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mirosimo.car_showroom2.model.Course;
import mirosimo.car_showroom2.service.CourseService;

@Controller
public class CourseController {
	@Autowired
	CourseService courseService;
	
	/*
	 * View where is displayed list of educational courses
	 */
	@GetMapping("/course-list")
	public String listCourseView(Model model) {
		model.addAttribute("listCourse", courseService.getAllEntities());
		return "course-list";
	}
	
	/* View for adding new course */
	@GetMapping("/course-new")
	public String newCourseView(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "course-new";
	} 
	
	/* Saving new course */
	@PostMapping("/course-save")
	public String saveCountry(@ModelAttribute("course") Course course) {										
		this.courseService.saveEntity(course);
		return "redirect:/course-list";
	}
	
	/* Deleting country */
	@GetMapping("/course-delete/{id}")
	public String deleteItem(@PathVariable (value="id") long id) {
		this.courseService.deleteEntityById(id);
		return "redirect:/course-list";
	}
}
