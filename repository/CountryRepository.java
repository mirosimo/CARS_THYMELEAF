package com.mirosimo.car_showroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirosimo.car_showroom.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
