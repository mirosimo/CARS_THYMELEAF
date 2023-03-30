package com.mirosimo.car_showroom.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.mirosimo.car_showroom.model.Department;
import com.mirosimo.car_showroom.model.Education;
import com.mirosimo.car_showroom.service.DepartmentService;
import com.mirosimo.car_showroom.service.MenuService;

@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	MenuService menuService;
	
	@GetMapping("/department-list")
	public String listDepartmentView(Model model) {
		model.addAttribute("listDepartments", departmentService.getAllEntities());
		return "department-list";
	}
	
	@GetMapping("/department-new")
	public String newDepartmentView(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "department-new";
	} 
	
	@PostMapping("/department-save")
	public String savaDepartment(@Valid Department department, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("department", department);
			return "department-new";
		} 
		this.departmentService.saveEntity(department);
		return "redirect:/department-list";
	}
			
	@GetMapping("/department-delete/{id}")
	public String deleteDepartment(@PathVariable (value="id") long id) {
		this.departmentService.deleteEntityById(id);
		return "redirect:/department-list";
	}
}
