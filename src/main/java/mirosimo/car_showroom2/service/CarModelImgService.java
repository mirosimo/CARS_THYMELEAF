package mirosimo.car_showroom2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.CarModelImg;
import mirosimo.car_showroom2.repository.CarModelImgRepository;

@Service
public class CarModelImgService {
	@Autowired 
	private CarModelImgRepository carModelImgRepository;
		
	
	public List<CarModelImg> getAllEntities() {
		return carModelImgRepository.findAll();
	}
}
