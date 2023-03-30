package com.mirosimo.car_showroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.WorkPosition;
import com.mirosimo.car_showroom.repository.WorkPositionRepository;

@Service
public class WorkPositionService {
	@Autowired 
	private WorkPositionRepository workPositionRepository;
	
	public List<WorkPosition> getAllEntities() {
		return workPositionRepository.findAll();
	}
}
