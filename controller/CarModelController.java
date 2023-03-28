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
import com.mirosimo.car_showroom.model.CarBrand;
import com.mirosimo.car_showroom.model.CarModel;
import com.mirosimo.car_showroom.model.CarModelImg;
import com.mirosimo.car_showroom.service.CarBrandService;
import com.mirosimo.car_showroom.service.CarModelImgService;
import com.mirosimo.car_showroom.service.CarModelService;
import com.mirosimo.car_showroom.service.MenuService;


/* 
 * CarModel represent model of car Brand. E.g. Fabia, Octavia of brand Skoda
 * 
 *  */
@Controller
public class CarModelController {
	@Autowired
	CarModelService carModelService;
	
	@Autowired
	CarModelImgService carModelImgService;
	
	@Autowired
	CarBrandService carBrandService;
	
	@Autowired
	MenuService menuService;
	
	/* View with list of models in table form */	
	@GetMapping("/car-model-list/{brand-url-name}")
	public String listCarModelView(Model model, 
			@PathVariable (value="brand-url-name") String brandUrlName) {		
		model.addAttribute("listEntities", carModelService.getEntitiesByCarBrandName(brandUrlName));		
		model.addAttribute("menuItem", menuService.getMenuById(1));
		model.addAttribute("imgUtil", new ImageUtil());
		return "car-model-list";
	}
	
	/* View for adding new car model */
	@GetMapping("/car-model-new/{brand-url-name}")
	public String newCarModelView(Model model, 
			@PathVariable (value="brand-url-name") String brandUrlName) {
		CarModel carModel = new CarModel();
		CarBrand carBrand = carBrandService.getEntityByCarBrandUrlName(brandUrlName);
		carModel.setCarBrand(carBrand);
		model.addAttribute("carModel", carModel);
		model.addAttribute("imgUtil", new ImageUtil());				
		model.addAttribute("menuItem", menuService.getMenuById(1));
		return "car-model-new";
	} 
	
	@PostMapping("/car-model-save")
	public String saveCarModel(@ModelAttribute("carModel") CarModel carModel, 
			@RequestParam("image") MultipartFile file) throws IOException {
		byte[] imgData = file.getBytes();
		
		CarModelImg carModelImg = new CarModelImg();
		carModelImg.setImg(imgData);				
		
		carModel.getCarModelImgs().add(carModelImg);					
		this.carModelService.saveEntity(carModel);
		return "redirect:/car-model-list/"+carModel.getCarBrand().getUrlName();
	}
	
	@GetMapping("/car-model-delete/{brand-id}/{id}")
	public String deleteCarModel(@PathVariable (value="id") long id, 
								@PathVariable (value="brand-id") String brandUrlName) {
		this.carModelService.deleteEntityById(id);
		return "redirect:/car-model-list/"+brandUrlName;
	}
	
	@GetMapping("/car-model-update/{id}")
	public String updateCarModel(@PathVariable (value="id") long id, 
			Model model) {
		CarModel carModel = this.carModelService.getEntityById(id);
		model.addAttribute("carModel", carModel);
		
		/* listCarBrand is used for combo with car brands*/
		model.addAttribute("listCarBrand", carBrandService.getAllEntities());
		model.addAttribute("menuItem", menuService.getMenuById(1));
		return "car-model-update";
	}
	
	/* 
	 * View that contains grid with car models - of particular car brand.
	 * Each item of grid contains photo of car model and some additional information
	 *  
	 * */
	@GetMapping("/cars-grid-model/{brand-id}")
	public String gridCarModelView(@PathVariable (value="brand-id") String brandUrlName, Model model) {
		
		model.addAttribute("listEntities", carModelService.getEntitiesByCarBrandName(brandUrlName));		
		model.addAttribute("imgUtil", new ImageUtil());
		model.addAttribute("menuItem", menuService.getMenuById(1));
		
		return "cars-grid-model";
	}
	
}
