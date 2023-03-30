package com.mirosimo.car_showroom.controller;

import java.io.IOException;

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
import com.mirosimo.car_showroom.service.CountryService;
import com.mirosimo.car_showroom.service.MenuService;

@Controller
public class CountryController {
	@Autowired
	CountryService countryService;
	
	@Autowired
	MenuService menuService;
	
	/*
	 * View where is displayed list of countries
	 */
	@GetMapping("/country-list")
	public String listCountryView(Model model) {
		model.addAttribute("listCountry", countryService.getAllEntities());
		model.addAttribute("imgUtil", new ImageUtil());
		return "country-list";
	}
	
	/* View for adding new country */
	@GetMapping("/country-new")
	public String newCountryView(Model model) {
		Country country = new Country();
		model.addAttribute("country", country);
		return "country-new";
	} 
	
	/* Saving new country */
	@PostMapping("/country-save")
	public String saveCountry(@ModelAttribute("country") Country country, 
			@RequestParam("flag2") MultipartFile flagImgBinary) throws IOException {
		byte[] imgBytes = flagImgBinary.getBytes();		
		
		country.setFlag(imgBytes);
		
		this.countryService.saveEntity(country);
		return "redirect:/country-list";
	}
	
	/* Deleting country */
	@GetMapping("/country-delete/{id}")
	public String deleteItem(@PathVariable (value="id") long id) {
		this.countryService.deleteEntityById(id);
		return "redirect:/country-list";
	}
}
