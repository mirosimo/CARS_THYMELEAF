package mirosimo.car_showroom2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mirosimo.car_showroom2.model.EmployeeImg;
import mirosimo.car_showroom2.repository.EmployeeImgRepository;

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
