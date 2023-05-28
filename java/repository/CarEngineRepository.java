package mirosimo.car_showroom2.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mirosimo.car_showroom2.model.CarEngine;

public interface CarEngineRepository extends JpaRepository<CarEngine, Long> {
	
	/* 
	 * id - id of Car Brand
	 * Get all engenes assigned to particular Car Brand */
	public List<CarEngine> findByCarBrand_id(Long id);
	
	/* 
	 * urlName - urlName of Car Brand
	 * Get all engenes assigned to particular Car Brand */
	public List<CarEngine> findByCarBrand_urlName(String urlName);
}
