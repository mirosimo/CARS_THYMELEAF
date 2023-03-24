package com.mirosimo.car_showroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.CarBrand;
import com.mirosimo.car_showroom.model.Country;
import com.mirosimo.car_showroom.repository.CountryRepository;

@Service
public class CountryService {
	@Autowired 
	private CountryRepository countryRepository;
	
	public List<Country> getAllEntities() {
		return countryRepository.findAll();
	}
	
	public void saveEntity(Country entity) {
		this.countryRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.countryRepository.deleteById(id);
	}
}
