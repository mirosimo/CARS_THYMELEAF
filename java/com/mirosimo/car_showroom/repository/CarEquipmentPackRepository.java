package com.mirosimo.car_showroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirosimo.car_showroom.model.CarEquipmentPack;

public interface CarEquipmentPackRepository extends  JpaRepository<CarEquipmentPack, Long>{
	
	/*  
	 * Get equipmentPacks for particular model of car brand
	 * */
	public List<CarEquipmentPack> 
		findByCarModel_carBrand_urlNameAndCarModel_urlName(String urlCarBrand, String urlCarModel);
	
	public List<CarEquipmentPack> 
	findByCarModel_urlName(String urlCarModel);
}
