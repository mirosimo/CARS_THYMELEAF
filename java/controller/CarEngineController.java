package mirosimo.car_showroom2.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mirosimo.car_showroom2.Utils.ImageUtil;
import mirosimo.car_showroom2.model.CarEngine;
import mirosimo.car_showroom2.service.CarBrandService;
import mirosimo.car_showroom2.service.CarEngineService;
import mirosimo.car_showroom2.service.CarEquipmentPackService;


@Controller
public class CarEngineController {
	@Autowired
	CarBrandService carBrandService;
	
	@Autowired
	CarEngineService carEngineService;
	
	@Autowired
	CarEquipmentPackService carEquipmentPackService;

	
	/* 
	 * View with all CarEngine entities for particular car brand
	 *   
	 * The required ordering of CarEngine entities is by 3 following fields 
	 * 
	 * - propellantType - is enum type
	 * - engineGroup
	 * - engineName 
	 * 
	 * */
	@GetMapping("/car-engine-list/{url_brand}")
	public String getCarEngineBrandList(Model model, 
			@PathVariable (value="url_brand") String urlCarBrand) {
		
		List<CarEngine> listEntities = this.carEngineService.findByCarBrand_urlName(urlCarBrand).stream()
				.sorted(CarEngine.getComparatorByFuelGroupName())
				.collect(Collectors.toList());
		model.addAttribute("listEntities", listEntities);
		model.addAttribute("imgUtil", new ImageUtil());	
		return "car-engine-list";
	}
	
	
	/* 
	 * Displays View for adding new Engine type for particular car Brand  
	 * Than this engine could be assign to one ore more car equipment packs 
	 * 
	 * */		
	@GetMapping("/car-engine-new/{url_brand}")
	public String newCarEngineBrandView(Model model, 
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
			model.addAttribute("imgUtil", new ImageUtil());
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
