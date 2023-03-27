package com.mirosimo.car_showroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirosimo.car_showroom.model.CarBrand;
import com.mirosimo.car_showroom.model.CarModel;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> { 	
	
	public CarBrand findByUrlName(String name);
}
