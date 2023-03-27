package com.mirosimo.car_showroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.CarModelImg;
import com.mirosimo.car_showroom.repository.CarModelImgRepository;

@Service
public class CarModelImgService {
	@Autowired 
	private CarModelImgRepository carModelImgRepository;
		
	
	public List<CarModelImg> getAllEntities() {
		return carModelImgRepository.findAll();
	}
}
