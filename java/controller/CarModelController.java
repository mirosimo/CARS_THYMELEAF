package mirosimo.car_showroom2.controller;

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
import mirosimo.car_showroom2.model.CarModel;
import mirosimo.car_showroom2.model.CarModelImg;
import mirosimo.car_showroom2.service.CarBrandService;
import mirosimo.car_showroom2.service.CarModelImgService;
import mirosimo.car_showroom2.service.CarModelService;


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
	
	/* View with list of models in table form */	
	@GetMapping("/car-model-list/{brand-url-name}")
	public String listCarModelView(Model model, 
			@PathVariable (value="brand-url-name") String brandUrlName) {		
		model.addAttribute("listEntities", carModelService.getEntitiesByCarBrandName(brandUrlName));		
		return "car-model-list";
	}
	
	/* View for adding new car model */
	@GetMapping("/car-model-new/{brand-url-name}")
	public String newCarModelView(Model model, 
			@PathVariable (value="brand-url-name") String brandUrlName) {				
		CarModel carModel = new CarModel();		
		carModel.setCarBrand(carBrandService.findEntityByCarBrandUrlName(brandUrlName));
		model.addAttribute("carModel", carModel);				
		return "car-model-new";
	} 
	
	@PostMapping("/car-model-save")
	public String saveCarModel(@Valid CarModel carModel, BindingResult result,
			Model model,
			@RequestParam("image") MultipartFile carModelImgFile) throws IOException 
	{
		if (result.hasErrors()) {
			model.addAttribute("carModel", carModel);	
			return "car-model-new";
		}
		byte[] imgModelBytes = carModelImgFile.getBytes();
		
		CarModelImg carModelImg = new CarModelImg();
		carModelImg.setImg(imgModelBytes);				
		
		carModel.getCarModelImgs().add(carModelImg);					
		this.carModelService.saveEntity(carModel);
		return "redirect:/car-model-list/"+carModel.getCarBrand().getUrlName();
	}
	
	/*
	 * Deletes car model by id
	 */
	@GetMapping("/car-model-delete/{brand-id}/{id}")
	public String deleteCarModel(@PathVariable (value="id") long id, 
								@PathVariable (value="brand-id") String brandUrlName) {
		this.carModelService.deleteEntityById(id);
		return "redirect:/car-model-list/"+brandUrlName;
	}
	
	
	
	/* 
	 * View that contains grid with car models - of particular car brand.
	 * Each item of grid contains photo of car model and some additional information
	 *  
	 * */
	@GetMapping("/cars-grid-model/{brand-id}")
	public String gridCarModelView(@PathVariable (value="brand-id") String brandUrlName, Model model) {		
		model.addAttribute("listEntities", carModelService.getEntitiesByCarBrandName(brandUrlName));				
		return "cars-grid-model";
	}
	
}
