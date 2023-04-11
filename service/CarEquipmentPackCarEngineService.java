package com.mirosimo.car_showroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.CarEquipmentPackCarEngine;
import com.mirosimo.car_showroom.repository.CarEquipmentPackCarEngineRepository;

@Service
public class CarEquipmentPackCarEngineService {
	@Autowired 
	CarEquipmentPackCarEngineRepository repository; 
	
	public CarEquipmentPackCarEngine saveEntity(CarEquipmentPackCarEngine entity) {
		return repository.save(entity);
	}
}
