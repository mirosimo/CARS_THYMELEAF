package mirosimo.car_showroom2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.UserRole;
import mirosimo.car_showroom2.repository.UserRoleRepository;


@Service
public class UserRoleService {
	@Autowired 
	private UserRoleRepository userRoleRepository;
	
	public List<UserRole> getAllEntities() {
		return userRoleRepository.findAll();
	}
	
	public UserRole saveEntity(UserRole entity) {
		return this.userRoleRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.userRoleRepository.deleteById(id);
	}
}
