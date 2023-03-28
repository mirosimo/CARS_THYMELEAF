package com.mirosimo.car_showroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.mirosimo.car_showroom.model.Education;
import com.mirosimo.car_showroom.service.EducationService;
import com.mirosimo.car_showroom.service.MenuService;

@Controller
public class EducationControlller {
	@Autowired
	EducationService educationService;
	
	@Autowired
	MenuService menuService;
	
	@GetMapping("/education-list")
	public String listEducationView(Model model) {
		model.addAttribute("listEducation", educationService.getAllEntities());
		model.addAttribute("menuItem", menuService.getMenuById(1));
		return "education-list";
	}
	
	@GetMapping("/education-new")
	public String newEducationView(Model model) {
		Education education = new Education();
		model.addAttribute("education", education);
		model.addAttribute("menuItem", menuService.getMenuById(1));
		return "education-new";
	} 
	
	@PostMapping("/education-save")
	public String saveEntity(@ModelAttribute("education") Education education) {
		this.educationService.saveEntity(education);
		return "redirect:/education-list";
	}
			
	@GetMapping("/education-delete/{id}")
	public String deleteEntity(@PathVariable (value="id") long id) {
		this.educationService.deleteEntityById(id);
		return "redirect:/education-list";
	}
}
