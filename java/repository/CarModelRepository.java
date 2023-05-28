package mirosimo.car_showroom2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mirosimo.car_showroom2.model.CarModel;

public interface CarModelRepository extends  JpaRepository<CarModel, Long>{
	public List<CarModel> findByCarBrand_id(Long id);
	
	public List<CarModel> findByCarBrand_urlNameOrderByEntityOrder(String name);
}
