package mirosimo.car_showroom2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.WorkPosition;
import mirosimo.car_showroom2.repository.WorkPositionRepository;

@Service
public class WorkPositionService {
	@Autowired 
	private WorkPositionRepository workPositionRepository;
	
	public List<WorkPosition> getAllEntities() {
		return workPositionRepository.findAll();
	}
}
