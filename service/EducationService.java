package mirosimo.car_showroom2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.Education;
import mirosimo.car_showroom2.repository.EducationRepository;

@Service
public class EducationService {
	@Autowired 
	private EducationRepository educationRepository;
	
	public List<Education> getAllEntities() {
		return educationRepository.findAll();
	}	
		
	
	public void saveEntity(Education entity) {
		this.educationRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.educationRepository.deleteById(id);
	}
}
