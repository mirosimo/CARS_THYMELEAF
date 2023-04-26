package mirosimo.car_showroom2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.exception.ApiCarException;
import mirosimo.car_showroom2.model.CarBrand;
import mirosimo.car_showroom2.repository.CarBrandRepository;

@Service
public class CarBrandService {
	@Autowired 
	private CarBrandRepository carBrandRepository;
	
	public List<CarBrand> getAllEntities() {
		return carBrandRepository.findAll();
	}
	
	public void saveEntity(CarBrand entity) {
		this.carBrandRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.carBrandRepository.deleteById(id);
	}
	
	public CarBrand findEntityByCarBrandUrlName(String brandUrlName) {
		if (this.carBrandRepository.findByUrlName(brandUrlName) != null) {
			return this.carBrandRepository.findByUrlName(brandUrlName);
		} else {		
			ApiCarException err = new ApiCarException("app.error.car-brand-not-found");
			throw err;
		}
	}
	
	public CarBrand getEntityById(long id) {
		Optional<CarBrand> optional = this.carBrandRepository.findById(id);
		CarBrand entity = null;
		if (optional.isPresent()) {
			entity = optional.get();
		} else {
			//throw new RuntimeException(" Not found car brand ID: " + id);
			//throw new IllegalStateException(" Not found car brand ID: " + id);
			throw new ApiCarException("Custooom exception");
		}
		return entity;
	}
}
