package com.mirosimo.car_showroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.CarEngine;
import com.mirosimo.car_showroom.repository.CarBrandRepository;
import com.mirosimo.car_showroom.repository.CarEngineRepository;

@Service
public class CarEngineService {
	@Autowired
	CarEngineRepository carEngineRepository;
	
	@Autowired
	CarBrandService carBrandService;
	
	public List<CarEngine> getEntitiesByCarBrandUrlName(String carBrand) {
		return carEngineRepository.findByCarBrand_id(carBrandService.getEntityByCarBrandUrlName(carBrand).getId());
	}
	
	public void saveEntity(CarEngine entity) {
		this.carEngineRepository.save(entity);
	}
	
	public void deleteEntityById(long id) {
		this.carEngineRepository.deleteById(id);
	}
	
	public CarEngine getEntityById(long id) {
		Optional<CarEngine> optional = this.carEngineRepository.findById(id);
		CarEngine entity = null;
		if (optional.isPresent()) {
			entity = optional.get();
		} else {
			throw new RuntimeException(" Not found car engine ID: " + id);
		}
		return entity;
	}
		
}
