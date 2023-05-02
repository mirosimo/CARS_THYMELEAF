package mirosimo.car_showroom2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.exception.ApiCarException;
import mirosimo.car_showroom2.model.User;
import mirosimo.car_showroom2.repository.UserRepository;

@Service
public class UserService {
	@Autowired 
	private UserRepository userRepository;
	
	public List<User> getAllEntities() {
		return userRepository.findAll();
	}
	
	public void saveEntity(User entity) {
		this.userRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.userRepository.deleteById(id);
	}
	
	public User getUserById(long id) {
		Optional<User> optional = this.userRepository.findById(id);
		User entity = null;
		if (optional.isPresent()) {
			entity = optional.get();
		} else {
			//throw new RuntimeException(" Not found car brand ID: " + id);
			//throw new IllegalStateException(" Not found car brand ID: " + id);
			throw new ApiCarException("Custooom exception");
		}
		return entity;
	}
}
