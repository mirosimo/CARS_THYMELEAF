package mirosimo.car_showroom2.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import mirosimo.car_showroom2.model.CarBrand;



public interface CarBrandRepository extends JpaRepository<CarBrand, Long> { 	
	
	public CarBrand findByUrlName(String name);
}
