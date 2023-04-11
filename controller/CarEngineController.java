package com.mirosimo.car_showroom.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mirosimo.car_showroom.Utils.ImageUtil;
import com.mirosimo.car_showroom.model.CarEngine;
import com.mirosimo.car_showroom.service.CarBrandService;
import com.mirosimo.car_showroom.service.CarEngineService;
import com.mirosimo.car_showroom.service.CarEquipmentPackService;


@Controller
public class CarEngineController {
	@Autowired
	CarBrandService carBrandService;
	
	@Autowired
	CarEngineService carEngineService;
	
	@Autowired
	CarEquipmentPackService carEquipmentPackService;

	
	/* View with all engine types for particular car brand - All Engine list*/
	@GetMapping("/car-engine-list/{url_brand}")
	public String getEngineListAll(Model model, 
			@PathVariable (value="url_brand") String urlCarBrand) {
		model.addAttribute("listEntities", this.carEngineService.findByCarBrand_urlName(urlCarBrand));
		model.addAttribute("imgUtil", new ImageUtil());	
		return "car-engine-list";
	}
	
	/* TO DO */
	/* View with all engine types available for particular car model */
	@GetMapping("/car-engine-list/{url_brand}/{url_model}")
	public String getEngineListModel(Model model, 
			@PathVariable (value="url_brand") String urlBrand,
			@PathVariable (value="url_model") String urlModel) {
		model.addAttribute("listEntities", carEquipmentPackService.getEntitesByCarBrandAndCarModel(urlBrand, urlModel));		
		return "car-engine-list";
	}
	
	/* TO DO */
	/* View with all engine types available for particular equipment_pack */
	@GetMapping("/car-engine-list/{url_brand}/{url_model}/{url_equipmant_pack}")
	public String getEngineListModelEquip(Model model, 
			@PathVariable (value="url_brand") String urlBrand,
			@PathVariable (value="url_model") String urlModel) {
		model.addAttribute("listEntities", carEquipmentPackService.getEntitesByCarBrandAndCarModel(urlBrand, urlModel));		
		return "car-engine-list";
	}
	
	/* Displays View for adding new Engine type for particular car Brand  
	 * Than this engine could be assign to one ore more car equipment packs */		
	@GetMapping("/car-engine-new/{url_brand}")
	public String newCarEngineForm(Model model, 
			@PathVariable (value="url_brand") String urlBrandName) {
		CarEngine carEngine = new CarEngine();		
		carEngine.setCarBrand(carBrandService.findEntityByCarBrandUrlName(urlBrandName));		
		model.addAttribute("carEngine", carEngine);
		model.addAttribute("imgUtil", new ImageUtil());	
		return "car-engine-new";
	} 
	
	/* Save Car Engine entity */
	@PostMapping("/car-engine-save")	
	public String saveCarEngine(@Valid CarEngine carEngine, BindingResult result, Model model)
		{				
		if (result.hasErrors()) {
			model.addAttribute("carEngine", carEngine);
			//return "car-engine-new/"+ carEngine.getCarBrand().getUrlName();
			return "car-engine-new";
		}
		this.carEngineService.saveEntity(carEngine);
		return "redirect:/car-engine-list/"+carEngine.getCarBrand().getUrlName();
	}
	
	/* Deletes Car Engine entity by Id */
	@GetMapping("/car-engine-delete/{url_brand}/{id}")
	public String deleteItem(@PathVariable (value="id") long id, @PathVariable (value="url_brand") String urlBrand ) {
		this.carEngineService.deleteEntityById(id);
		return "redirect:/car-engine-list/"+urlBrand;
	}
}
