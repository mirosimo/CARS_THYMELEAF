package com.mirosimo.car_showroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.CarEngine;
import com.mirosimo.car_showroom.repository.CarEngineRepository;

@Service
public class CarEngineService {
	@Autowired
	CarEngineRepository carEngineRepository;
	
	public void saveEntity(CarEngine entity) {
		this.carEngineRepository.save(entity);
	}
		
}
