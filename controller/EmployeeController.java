package mirosimo.car_showroom2.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mirosimo.car_showroom2.Utils.ImageUtil;
import mirosimo.car_showroom2.model.Address;
import mirosimo.car_showroom2.model.Country;
import mirosimo.car_showroom2.model.Course;
import mirosimo.car_showroom2.model.Department;
import mirosimo.car_showroom2.model.Education;
import mirosimo.car_showroom2.model.Employee;
import mirosimo.car_showroom2.model.Employee.Degree;
import mirosimo.car_showroom2.model.EmployeeCourse;
import mirosimo.car_showroom2.model.EmployeeImg;
import mirosimo.car_showroom2.model.WorkPosition;
import mirosimo.car_showroom2.service.CountryService;
import mirosimo.car_showroom2.service.CourseService;
import mirosimo.car_showroom2.service.DepartmentService;
import mirosimo.car_showroom2.service.EducationService;
import mirosimo.car_showroom2.service.EmployeeCourseService;
import mirosimo.car_showroom2.service.EmployeeService;
import mirosimo.car_showroom2.service.WorkPositionService;


@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;	
	
	@Autowired
	EmployeeCourseService employeeCourseService;	
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	WorkPositionService workPositionService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	EducationService educationService;


	/* View displays list of employees in table form */
	@GetMapping("/employee-list")
	public String listEmployeeView(Model model) {
		model.addAttribute("listEmployee", employeeService.getAllEmployees());		
		return "employee-list";
	}
	
	/* View for adding new employees into system */
	@GetMapping("/employee-new")
	public String newEmployeeView(Model model) {
		Employee employee = new Employee();
		Address address = new Address();		
		List<Department> listDpt = departmentService.getAllEntities();
		List<WorkPosition> listWorkPosition = workPositionService.getAllEntities(); 
		List<Country> listCountry = countryService.getAllEntities();
		List<Education> listEducation = educationService.getAllEntities();
		model.addAttribute("employee", employee);
		model.addAttribute("address", address);
		model.addAttribute("listDepartment", listDpt);
		model.addAttribute("listCountry", listCountry);
		model.addAttribute("listWorkPosition", listWorkPosition);
		model.addAttribute("listEducation", listEducation);
		return "employee-new";
	} 
	
	/* View displays detail information about particular Employee */
	@GetMapping("/employee-detail/{employeeId}")
	public String detailEmployeeView(@PathVariable Long employeeId, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
		model.addAttribute("imgUtil", new ImageUtil());			
		return "employee-detail";
	}
	
	@PostMapping("/employee-save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee,
								@ModelAttribute("address") Address address,
								@Validated Degree degreeCmb,
								@Validated String countryCmb,
			@RequestParam("image") MultipartFile file) throws IOException {
		System.out.println("Country id je: " + countryCmb);		
		byte[] imgData = file.getBytes();
		
		EmployeeImg employeeImg = new EmployeeImg();
		employeeImg.setImg(imgData);				
		
		System.out.println("Country jmeno je: "+countryService.getEntityById(Long.parseLong(countryCmb)).getName());
		address.setCountry(countryService.getEntityById(Long.parseLong(countryCmb)));
		employee.getAddressSet().add(address);
		employee.getEmployeeImgSet().add(employeeImg);
		employee.setDegree(degreeCmb);
		
		this.employeeService.saveEntity(employee);
		return "redirect:/employee-list";
	}
			
	/* View for Assigning Educational course to Employee */
	@GetMapping("/assign-course-to-employee")
	public String assignCourseToEmployee(Model model) {
		EmployeeCourse employeeCourse = new EmployeeCourse();
		List<Employee> listEmployee = employeeService.getAllEmployees();
		List<Course> listCourse = courseService.getAllEntities();
		model.addAttribute("employeeCourse", employeeCourse);
		model.addAttribute("listCourse", listCourse);
		model.addAttribute("listEmployee", listEmployee);
		return "employee-course-new";
	} 
	
	
	 @PostMapping("/employee-course-save")	
	 public String saveEmployeeCourse(@ModelAttribute("employeeCourse") EmployeeCourse employeeCourse,
	          @Validated String employeeCmb, @Validated String courseCmb)
	 {	     	     
	     Employee selectedEmployee = this.employeeService.getEmployeeById(Integer.parseInt(employeeCmb));
	     Course selectedCourse = this.courseService.getCourseById(Integer.parseInt(courseCmb));
	     employeeCourse.setEmployee(selectedEmployee);
	     employeeCourse.setCourse(selectedCourse);	   
	     employeeCourseService.saveEntity(employeeCourse);
	     
	     return "redirect:/employee-course-list/";
	 }	
}
