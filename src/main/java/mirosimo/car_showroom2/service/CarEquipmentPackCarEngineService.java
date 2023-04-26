package mirosimo.car_showroom2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.CarEquipmentPackCarEngine;
import mirosimo.car_showroom2.repository.CarEquipmentPackCarEngineRepository;

@Service
public class CarEquipmentPackCarEngineService {
	@Autowired 
	CarEquipmentPackCarEngineRepository repository; 
	
	public CarEquipmentPackCarEngine saveEntity(CarEquipmentPackCarEngine entity) {
		return repository.save(entity);
	}
}
