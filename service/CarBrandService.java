package com.mirosimo.car_showroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirosimo.car_showroom.model.CarBrand;
import com.mirosimo.car_showroom.repository.CarBrandRepository;

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
	
	public CarBrand getEntityByCarBrandUrlName(String brandUrlName) {
		return this.carBrandRepository.findByUrlName(brandUrlName);
	}
}
