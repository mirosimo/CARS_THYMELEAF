package com.mirosimo.car_showroom.controller;

import java.io.IOException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mirosimo.car_showroom.Utils.ImageUtil;
import com.mirosimo.car_showroom.exception.ApiCarException;
import com.mirosimo.car_showroom.model.CarEngine;
import com.mirosimo.car_showroom.model.CarEquipmentPack;
import com.mirosimo.car_showroom.model.CarEquipmentPackCarEngine;
import com.mirosimo.car_showroom.model.CarEquipmentPackImg;
import com.mirosimo.car_showroom.service.CarBrandService;
import com.mirosimo.car_showroom.service.CarEngineService;
import com.mirosimo.car_showroom.service.CarEquipmentPackCarEngineService;
import com.mirosimo.car_showroom.service.CarEquipmentPackService;
import com.mirosimo.car_showroom.service.CarModelService;

@Controller
public class CarEquipmentPackController {
	@Autowired
	CarEquipmentPackService carEquipmentPackService;
	
	@Autowired
	CarBrandService carBrandService;
	
	@Autowired
	CarModelService carModelService;
	
	@Autowired
	CarEngineService carEngineService;
	
	@Autowired
	CarEquipmentPackCarEngineService carEquipmentPackCarEngineService; 
	
	/* List of EquipmentPack for particular car brand and car model */	
	@GetMapping("/car-equipment-pack-list/{brand_url_name}/{model_url_name}")
	public String listEquipmentPackView(Model model, 
			@PathVariable (value="brand_url_name") String urlCarBrand, 
			@PathVariable (value="model_url_name") String urlCarModel) {		
		model.addAttribute("listEntities", carEquipmentPackService.getEntitesByCarBrandAndCarModel(urlCarBrand, urlCarModel));
		model.addAttribute("urlBrand", urlCarBrand);
		return "car-equipment-pack-list";
	}
	
	/* 
	 * Adding view appears for one particullar car brand. 

	 * In the View is combo box where user choose the model for which wants to
	 * add new equipment pack (e.g. Skoda Fabia has: Ambition, Style, Monte Carlo equipment packs ) and then follow 
	 * inputs for equipments pack name, photo etc...
	 * User can choose Engines which will be available for equipment pack.
	 * 
	 */
	@GetMapping("/car-equipment-pack-new/{brand_url_name}")
	public String newEquipmentPackView(Model model, 
			@PathVariable (value="brand_url_name") String urlCarBrand) {				
		try {
			model.addAttribute("brandLogoImg", new ImageUtil().getImgData(
					carBrandService.findEntityByCarBrandUrlName(urlCarBrand).getSmallLogoImg().getImg()));
			model.addAttribute("carEquipmentPack", new CarEquipmentPack());
			/* listCarModel - entities for models combobox */
			model.addAttribute("listCarModel", carModelService.getEntitiesByCarBrandName(urlCarBrand));
			model.addAttribute("listCarBrandEngines", carEngineService.findByCarBrand_urlName(urlCarBrand));
			
		} catch (ApiCarException e) {			
			model.addAttribute("apiCarException", e);		
			return "errors/error-page";
		}
		return "car-equipment-pack-new";
	} 
	
	@PostMapping("/car-equipment-pack-save")			
	public String saveItem(@Valid @ModelAttribute("carEquipmentPack") CarEquipmentPack carEquipmentPack,				
			BindingResult result,			
			Model model,
			//@RequestParam("carBrandEngines") CarEngine carEngine,
			@RequestParam("engine_checkbox") int[] engines,
			@RequestParam("image") MultipartFile imgFile) throws IOException {			
		if (result.hasErrors()) {
			model.addAttribute("carEquipmentPack", carEquipmentPack);
			/* listCarModel - entities for models combobox */			
			model.addAttribute("listCarModel", carModelService.getEntitiesByCarBrandName(
									carEquipmentPack.getCarModel().getCarBrand().getUrlName()));
			model.addAttribute("listCarBrandEngines", carEngineService.findByCarBrand_urlName(
									carEquipmentPack.getCarModel().getCarBrand().getUrlName()));
			model.addAttribute("imgUtil", new ImageUtil());
						
			return "car-equipment-pack-new";
		}						
		
		carEquipmentPack.getCarEquipmentPackImgs().add(new CarEquipmentPackImg(imgFile.getBytes()));
		
		// Oracle generates an Id. 
		CarEquipmentPack savedEquipmentPack = this.carEquipmentPackService.saveEntity(carEquipmentPack);						
		
		// Adding engines assigned to equipmant pack.
		for (int engineId : engines) {
			CarEngine engine = carEngineService.getEntityById(engineId); 
			CarEquipmentPackCarEngine carEquipmentPackCarEngine = new CarEquipmentPackCarEngine();
			carEquipmentPackCarEngine.setCarEngine(engine);		
			carEquipmentPackCarEngine.setCarEquipmentPack(savedEquipmentPack);
			carEquipmentPackCarEngine.setActive(true);
			carEquipmentPackCarEngineService.saveEntity(carEquipmentPackCarEngine);
		}
		
		
		return "redirect:/car-equipment-pack-list/"+carEquipmentPack.getCarModel().getCarBrand().getUrlName() + 
				"/"+ carEquipmentPack.getCarModel().getUrlName();
	}
	
	@GetMapping("/car-equipment-pack-delete/{id}")
	public String deleteItem(@PathVariable (value="id") long id) {
		this.carModelService.deleteEntityById(id);
		return "redirect:/car-model-list";
	}
}

