package com.mirosimo.car_showroom.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mirosimo.car_showroom.model.CarBrand;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> { 	
	
	public CarBrand findByUrlName(String name);
}
