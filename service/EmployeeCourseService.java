package com.mirosimo.car_showroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.EmployeeCourse;
import com.mirosimo.car_showroom.repository.EmployeeCourseRepository;

@Service
public class EmployeeCourseService {
	@Autowired 
	private EmployeeCourseRepository employeeCourseRepository;
	
	public List<EmployeeCourse> getAllEntities() {
		return employeeCourseRepository.findAll();
	}	
		
	
	public void saveEntity(EmployeeCourse entity) {
		employeeCourseRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		employeeCourseRepository.deleteById(id);
	}
}
