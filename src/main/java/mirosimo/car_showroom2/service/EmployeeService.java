package mirosimo.car_showroom2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.Employee;
import mirosimo.car_showroom2.model.MenuItem;
import mirosimo.car_showroom2.repository.EmployeeRepository;
import mirosimo.car_showroom2.repository.MenuRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	public List<MenuItem> getMenuTree() {
	  	return menuRepository.getMenuTree();
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public Employee saveEntity(Employee emp) {
		return this.employeeRepository.save(emp);		
	}
	
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = this.employeeRepository.findById(id);
		Employee emp = null;
		if (optional.isPresent()) {
			emp = optional.get();
		} else {
			throw new RuntimeException(" Not found employee ID: " + id);
		}
		return emp;
	}
}
