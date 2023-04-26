package mirosimo.car_showroom2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.CarModel;
import mirosimo.car_showroom2.repository.CarModelRepository;

@Service
public class CarModelService {
	@Autowired 
	private CarModelRepository carModelRepository;
		
	
	public List<CarModel> getAllEntities() {
		return carModelRepository.findAll();
	}
	
	public List<CarModel> getEntitiesByCarBrandId(Long id) {
		return this.carModelRepository.findByCarBrand_id(id);
	}
	
	public List<CarModel> getEntitiesByCarBrandName(String brandUrlName) {
		return this.carModelRepository.findByCarBrand_urlNameOrderByEntityOrder(brandUrlName);
	}
	
	public void saveEntity(CarModel entity) {
		this.carModelRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.carModelRepository.deleteById(id);
	}
	
	public CarModel getEntityById(long id) {
		Optional<CarModel> optional = this.carModelRepository.findById(id);
		CarModel entity = null;
		if (optional.isPresent()) {
			entity = optional.get();
		} else {
			throw new RuntimeException(" Not found car model ID: " + id);
		}
		return entity;
	}
}
