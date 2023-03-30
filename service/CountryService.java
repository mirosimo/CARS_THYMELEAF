package com.mirosimo.car_showroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.CarModel;
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
	
	public Country getEntityById(long id) {
		Optional<Country> optional = this.countryRepository.findById(id);
		Country entity = null;
		if (optional.isPresent()) {
			entity = optional.get();
		} else {
			throw new RuntimeException(" Not found country ID: " + id);
		}
		return entity;
	}
}
