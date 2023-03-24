package com.mirosimo.car_showroom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mirosimo.car_showroom.Utils.ImageUtil;
import com.mirosimo.car_showroom.model.Country;
import com.mirosimo.car_showroom.model.Department;
import com.mirosimo.car_showroom.model.Education;
import com.mirosimo.car_showroom.model.Employee;
import com.mirosimo.car_showroom.model.EmployeeImg;
import com.mirosimo.car_showroom.model.WorkPosition;
import com.mirosimo.car_showroom.service.CountryService;
import com.mirosimo.car_showroom.service.DepartmentService;
import com.mirosimo.car_showroom.service.EducationService;
import com.mirosimo.car_showroom.service.EmployeeService;
import com.mirosimo.car_showroom.service.MenuService;
import com.mirosimo.car_showroom.service.TrainingCourseService;
import com.mirosimo.car_showroom.service.WorkPositionService;


@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;	
	
	@Autowired
	TrainingCourseService courseService;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	WorkPositionService workPositionService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	EducationService educationService;
	
	@Autowired
	MenuService menuService;
	

	
	@GetMapping("/employee-list")
	public String listEmployeeView(Model model) {
		model.addAttribute("listEmployee", employeeService.getAllEmployees());		
		model.addAttribute("menuItem", menuService.getMenuById(1));
		return "employee-list";
	}
	
	@GetMapping("/employee-new")
	public String newEmployeeView(Model model) {
		Employee employee = new Employee();
		List<Department> listDpt = departmentService.getAllEntities();
		List<WorkPosition> listWorkPosition = workPositionService.getAllEntities(); 
		List<Country> listCountry = countryService.getAllEntities();
		List<Education> listEducation = educationService.getAllEntities();
		model.addAttribute("employee", employee);
		model.addAttribute("listDepartment", listDpt);
		model.addAttribute("listCountry", listCountry);
		model.addAttribute("listWorkPosition", listWorkPosition);
		model.addAttribute("listEducation", listEducation);
		model.addAttribute("menuItem", menuService.getMenuById(1));
		return "employee-new";
	} 
	
	@GetMapping("/employee-detail/{employeeId}")
	public String detailEmployeeView(@PathVariable Long employeeId, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
		model.addAttribute("imgUtil", new ImageUtil());			
		model.addAttribute("menuItem", menuService.getMenuById(1));		
		return "employee-detail";
	}
	
	@PostMapping("/employee-save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, 
			@RequestParam("image") MultipartFile file) throws IOException {
				
		byte[] imgData = file.getBytes();
		
		EmployeeImg employeeImg = new EmployeeImg();
		employeeImg.setImg(imgData);				
		
		employee.getEmployeeImgSet().add(employeeImg);
		this.employeeService.saveEntity(employee);
		return "redirect:/employee-list";
	}
	
		
	/*@PutMapping("/{employeeId}/courses/{courseId}")
	Employee assignCourseToEmployee(@PathVariable Long employeeId, @PathVariable Long courseId) {
		Employee emp = employeeService.getEmployeeById(employeeId);
		Course course = courseService.getCourseById(courseId);	
		emp.addCourse(course);
		return employeeService.saveEntity(emp);
	}*/
}
