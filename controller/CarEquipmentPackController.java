package com.mirosimo.car_showroom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.mirosimo.car_showroom.model.CarEquipmentPack;
import com.mirosimo.car_showroom.model.CarEquipmentPackImg;
import com.mirosimo.car_showroom.model.CarModel;
import com.mirosimo.car_showroom.service.CarEquipmentPackService;
import com.mirosimo.car_showroom.service.CarModelService;

@Controller
public class CarEquipmentPackController {
	@Autowired
	CarEquipmentPackService carEquipmentPackService;
	
	@Autowired
	CarModelService carModelService;
	
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
	 * Adding new Equipment pack is possible for one particullar car brand.
	 * e.g. Skoda, Audi,... 
	 * which is send by url (brand_url_name). 
	 * On the form is combo box where user choose the model for which wants to
	 * add new equipment pack (e.g. Skoda Fabia has: Ambition, Style, Monte Carlo equipment packs ) and then follow 
	 * inputs for (equipments pack name, photo, etc...)
	 * 
	 */
	@GetMapping("/car-equipment-pack-new/{brand_url_name}")
	public String newEquipmentPackView(Model model, 
			@PathVariable (value="brand_url_name") String brandUrlName) {
		CarEquipmentPack carEquipmentPack = new CarEquipmentPack();
		/* listCarModel - entities for models combobox */
		List<CarModel> listCarModel = carModelService.getEntitiesByCarBrandName(brandUrlName);
		model.addAttribute("carEquipmentPack", carEquipmentPack);
		model.addAttribute("listCarModel", listCarModel);
		return "car-equipment-pack-new";
	} 
	
	@PostMapping("/car-equipment-pack-save")
	public String saveItem(@ModelAttribute("carEquipmentPack") CarEquipmentPack carEquipmentPack, 
			@RequestParam("image") MultipartFile file) throws IOException {
		byte[] imgData = file.getBytes();
		
		CarEquipmentPackImg carEquipmentPackImg = new CarEquipmentPackImg();
		carEquipmentPackImg.setImg(imgData);				
		
		carEquipmentPack.getCarEquipmentPackImgs().add(carEquipmentPackImg);
		this.carEquipmentPackService.saveEntity(carEquipmentPack);
		return "redirect:/car-equipment-pack-list/"+carEquipmentPack.getCarModel().getCarBrand().getUrlName() + 
				"/"+ carEquipmentPack.getCarModel().getUrlName();
	}
	
	@GetMapping("/car-equipment-pack-delete/{id}")
	public String deleteItem(@PathVariable (value="id") long id) {
		this.carModelService.deleteEntityById(id);
		return "redirect:/car-model-list";
	}
}

