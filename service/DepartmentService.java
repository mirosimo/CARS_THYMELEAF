package com.mirosimo.car_showroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.Department;
import com.mirosimo.car_showroom.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired 
	private DepartmentRepository departmentRepository;
	
	public List<Department> getAllEntities() {
		return departmentRepository.findAll();
	}
	
	public void saveEntity(Department entity) {
		this.departmentRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.departmentRepository.deleteById(id);
	}
}
