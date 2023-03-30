package com.mirosimo.car_showroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mirosimo.car_showroom.model.CarEngine;
import com.mirosimo.car_showroom.model.CarEquipmentPack;
import com.mirosimo.car_showroom.service.CarEngineService;
import com.mirosimo.car_showroom.service.CarEquipmentPackService;
import com.mirosimo.car_showroom.service.MenuService;

@Controller
public class CarEngineController {
	@Autowired
	CarEngineService carEngineService;
	
	@Autowired
	CarEquipmentPackService carEquipmentPackService;
	
	@Autowired
	MenuService menuService;

	
	@GetMapping("/car-engine-list/{url_brand}/{url_model}")
	public String getEngineList(Model model, 
			@PathVariable (value="url_brand") String urlBrand,
			@PathVariable (value="url_model") String urlModel) {
		model.addAttribute("listEntities", carEquipmentPackService.getEntitesByCarBrandAndCarModel(urlBrand, urlModel));		
		return "car-engine-list";
	}
	
			
	@GetMapping("/car-engine-new/{url_brand}/{url_model}")
	public String newCarEngineForm(Model model, 
			@PathVariable (value="url_brand") String urlBrandName,
			@PathVariable (value="url_model") String urlModelName) {
		CarEngine carEngine = new CarEngine();
		CarEquipmentPack chooseEquipmentPack = new CarEquipmentPack(); 
		
		List<CarEquipmentPack> listCarEquipmentPack = 
				carEquipmentPackService.getEntitesByCarModelName(urlModelName);
		model.addAttribute("chooseEquipmentPack", chooseEquipmentPack);
		model.addAttribute("carEngine", carEngine);
		model.addAttribute("listCarEquipmentPack", listCarEquipmentPack);		
		return "car-engine-new";
	} 
	
	@PostMapping("/car-engine-save")	
	public String savaCarBrand(@ModelAttribute("carEngine") CarEngine carEngine,
			@Validated String equipmentPack)
		{
		
		CarEquipmentPack equipmentPackObj = carEquipmentPackService.getEntityById(Long.parseLong(equipmentPack));		
		carEngine.getCarEquipmentPacks().add(equipmentPackObj);
		
		this.carEngineService.saveEntity(carEngine);
		return "redirect:/car-engine-list/";
	}
}
