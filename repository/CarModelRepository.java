package com.mirosimo.car_showroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirosimo.car_showroom.model.CarModel;

public interface CarModelRepository extends  JpaRepository<CarModel, Long>{
	public List<CarModel> findByCarBrand_id(Long id);
	
	public List<CarModel> findByCarBrand_urlNameOrderByEntityOrder(String name);
}
