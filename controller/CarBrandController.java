package com.mirosimo.car_showroom.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mirosimo.car_showroom.model.CarBrand;
import com.mirosimo.car_showroom.model.CarBrandImg;
import com.mirosimo.car_showroom.service.CarBrandService;
import com.mirosimo.car_showroom.service.MenuService;

@Controller
public class CarBrandController {
	@Autowired
	CarBrandService carBrandService;
	
	@Autowired
	MenuService menuService;
	
	/*
	 * View where is displayed car brand list in table form
	 */
	@GetMapping("/car-brand-list")
	public String listCarBrandView(Model model) {
		model.addAttribute("listCarBrand", carBrandService.getAllEntities());		
		return "car-brand-list";
	}
	
	/* View for adding new car brand */
	@GetMapping("/car-brand-new")
	public String newCarBrandView(Model model) {
		CarBrand carBrand = new CarBrand();
		model.addAttribute("carBrand", carBrand);		
		return "car-brand-new";
	} 
	
	/* Saves Car Brand 
	 * If user inserts wrong data into inputs, than is displayed  again 
	 * view for adding new data with allert announcement. 
	 * When is everithing OK, data are saved . */
	@PostMapping("/car-brand-save")
	//public String saveCarBrand(@ModelAttribute("carBrand") CarBrand carBrand, 
	public String saveCarBrand(@Valid CarBrand carBrand, BindingResult result, Model model,
			@RequestParam("img-main") MultipartFile viewMainImg,
			@RequestParam("img-logo-small") MultipartFile viewImgLogoSmall, 
			@RequestParam("img-logo-big") MultipartFile viewImgLogoBig) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("carBrand", carBrand);
			return "car-brand-new";
		}
											
		carBrand.getCarBrandImgSet().add(new CarBrandImg(CarBrandImg.ImgType.BRAND_IMG, viewMainImg.getBytes()));
		carBrand.getCarBrandImgSet().add(new CarBrandImg(CarBrandImg.ImgType.BRAND_LOGO_SMALL, viewImgLogoSmall.getBytes()));
		carBrand.getCarBrandImgSet().add(new CarBrandImg(CarBrandImg.ImgType.BRAND_LOGO, viewImgLogoBig.getBytes()));
				
		this.carBrandService.saveEntity(carBrand);
		return "redirect:/car-brand-list";
	}
	
	/* Deletes Car Brand entity by Id */
	@GetMapping("/car-brand-delete/{id}")
	public String deleteItem(@PathVariable (value="id") long id) {
		this.carBrandService.deleteEntityById(id);
		return "redirect:/car-brand-list";
	}
}
