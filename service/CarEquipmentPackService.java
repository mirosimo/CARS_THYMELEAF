package com.mirosimo.car_showroom.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirosimo.car_showroom.model.CarEquipmentPack;
import com.mirosimo.car_showroom.repository.CarEquipmentPackRepository;

@Service
public class CarEquipmentPackService {
	@Autowired
	CarEquipmentPackRepository carEquipmentPackRepository;
	
	public List<CarEquipmentPack> getAllEntities() {
		return carEquipmentPackRepository.findAll();
	}
			
	public List<CarEquipmentPack> getEntitesByCarBrandAndCarModel(String urlCarBrand, String urlCarModel) {
		 return carEquipmentPackRepository
			.findByCarModel_carBrand_urlNameAndCarModel_urlName(urlCarBrand, urlCarModel);
	}
	
	
	public List<CarEquipmentPack> getEntitesByCarModelName(String urlCarModel) {
		 return carEquipmentPackRepository.findByCarModel_urlName(urlCarModel);			
	}
	
	public CarEquipmentPack getEntityById(long id) {
		Optional<CarEquipmentPack> optional = this.carEquipmentPackRepository.findById(id);
		CarEquipmentPack entity = null;
		if (optional.isPresent()) {
			entity = optional.get();
		} else {
			throw new RuntimeException("CarEquipmentPack Not found" + id);
		}
		return entity;
	}
	
	public void saveEntity(CarEquipmentPack entity) {
		this.carEquipmentPackRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.carEquipmentPackRepository.deleteById(id);
	}
}
