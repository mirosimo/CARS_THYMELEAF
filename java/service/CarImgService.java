package mirosimo.car_showroom2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.CarImg;
import mirosimo.car_showroom2.repository.CarImgRepository;

@Service
public class CarImgService {
	
	@Autowired
	CarImgRepository carImgRepository;
	
	// Save entity
	public void saveEntity(CarImg entity) {
		this.carImgRepository.save(entity);
	}
	
	// Get all entities
	public List<CarImg> getAllEntities() {
		return carImgRepository.findAll();
	}
}
