package com.mirosimo.car_showroom.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.mirosimo.car_showroom.model.Department;
import com.mirosimo.car_showroom.service.DepartmentService;
import com.mirosimo.car_showroom.service.MenuService;

@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
	/* View displays list of departments - in table form */
	@GetMapping("/department-list")
	public String listDepartmentView(Model model) {
		model.addAttribute("listDepartments", departmentService.getAllEntities());
		return "department-list";
	}
	
	/* View for adding new Department into system */
	@GetMapping("/department-new")
	public String newDepartmentView(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "department-new";
	} 
	
	/* Saves department 
	 * If user inserts wrong data into inputs, than is displayed  again 
	 * view for adding new data with allert announcement. 
	 * When is everithing OK, data are saved . */
	@PostMapping("/department-save")
	public String savaDepartment(@Valid Department department, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("department", department);
			return "department-new";
		} 
		this.departmentService.saveEntity(department);
		return "redirect:/department-list";
	}
	
	/* Delete departments from system */
	@GetMapping("/department-delete/{id}")
	public String deleteDepartment(@PathVariable (value="id") long id) {
		this.departmentService.deleteEntityById(id);
		return "redirect:/department-list";
	}
}
