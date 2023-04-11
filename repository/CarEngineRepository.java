package com.mirosimo.car_showroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirosimo.car_showroom.model.CarEngine;

public interface CarEngineRepository extends JpaRepository<CarEngine, Long> {
	
	/* 
	 * id - id of Car Brand
	 * Get all engenes assigned to particular Car Brand */
	public List<CarEngine> findByCarBrand_id(Long id);
	
	/* 
	 * urlName - urlName of Car Brand
	 * Get all engenes assigned to particular Car Brand */
	public List<CarEngine> findByCarBrand_urlName(String urlName);
}
