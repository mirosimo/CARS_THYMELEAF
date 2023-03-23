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
		model.addAttribute("menuItem", menuService.getMenuById(1));		
		return "car-brand-list";
	}
	
	/* View for adding new car brand */
	@GetMapping("/car-brand-new")
	public String newCarBrandView(Model model) {
		CarBrand carBrand = new CarBrand();
		model.addAttribute("carBrand", carBrand);
		model.addAttribute("menuItem", menuService.getMenuById(1));		
		return "car-brand-new";
	} 
	
	@PostMapping("/car-brand-save")
	public String saveCarBrand(@ModelAttribute("carBrand") CarBrand carBrand, 
			@RequestParam("img-main") MultipartFile mainImg,
			@RequestParam("img-logo-small") MultipartFile imgLogoSmall, 
			@RequestParam("img-logo-big") MultipartFile imgLogoBig) throws IOException {
		byte[] imgMainData = mainImg.getBytes();
		byte[] imgLogoSmallData = imgLogoSmall.getBytes();
		byte[] imgLogoBigData = imgLogoBig.getBytes();
		
		CarBrandImg carBrandImg = new CarBrandImg();
		carBrandImg.setImgType(CarBrandImg.ImgType.BRAND_IMG);
		carBrandImg.setImg(imgMainData);
		
		CarBrandImg logoSmall = new CarBrandImg();
		logoSmall.setImgType(CarBrandImg.ImgType.BRAND_LOGO_SMALL);		
		logoSmall.setImg(imgLogoSmallData);
		
		CarBrandImg logoBig = new CarBrandImg();
		logoBig.setImgType(CarBrandImg.ImgType.BRAND_LOGO);		
		logoBig.setImg(imgLogoBigData);
		
		carBrand.getCarBrandImgSet().add(carBrandImg);
		carBrand.getCarBrandImgSet().add(logoSmall);
		carBrand.getCarBrandImgSet().add(logoBig);
		this.carBrandService.saveEntity(carBrand);
		return "redirect:/car-brand-list";
	}
	
	@GetMapping("/car-brand-delete/{id}")
	public String deleteItem(@PathVariable (value="id") long id) {
		this.carBrandService.deleteEntityById(id);
		return "redirect:/car-brand-list";
	}
}
