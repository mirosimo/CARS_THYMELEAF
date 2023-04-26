package mirosimo.car_showroom2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.Role;
import mirosimo.car_showroom2.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired 
	private RoleRepository roleRepository;
	
	public List<Role> getAllEmployees() {
		return roleRepository.findAll();
	}
}
