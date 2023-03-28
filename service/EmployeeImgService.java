package com.mirosimo.car_showroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mirosimo.car_showroom.model.EmployeeImg;
import com.mirosimo.car_showroom.repository.EmployeeImgRepository;

public class EmployeeImgService {
	@Autowired
	EmployeeImgRepository employeeImgRepository;
	
	public List<EmployeeImg> getAllEntities() {
		return employeeImgRepository.findAll();
	}	
		
	
	public void saveEntity(EmployeeImg entity) {
		this.employeeImgRepository.save(entity);
	}
}
