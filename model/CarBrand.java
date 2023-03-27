package com.mirosimo.car_showroom.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * Represent Car Brand e.g. Skoda, Ford, Renault,... 
 * Car Brand is root of hierarchy
 * 
 * 		  1 : N        1 : N		          1	: N
 * CarBrand --> CarModel --> CarEquipmentPack --> CarEngine
 * 
 * */
@Table
@Entity
public class CarBrand {
	@Id
	@SequenceGenerator(
			name = "car_brand_sequence",
			sequenceName = "car_brand_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "car_brand_sequence"
	)
	private long id;
	
	private String name;
	
	// namme used for url (without diacritic)
	private String urlName;		
	private String descShort;
	private String descLong;
	
	private boolean active;
	
	public CarBrand() {
		this.active = true;
	}
	
	/* 
	 * This set contains image objects related to  particular car Brand 
	 * Big, Small Brand logo, etc...
	 * 
	 * */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_car_brand_img_id", referencedColumnName = "id")
	private Set<CarBrandImg> carBrandImgSet = new HashSet<>();

	/* Return object containing big logo img (in bytes form) */
	public CarBrandImg getBigLogoImg() {		
		return this.carBrandImgSet.stream()
						.filter(imgObj -> imgObj.getImgType().equals(CarBrandImg.ImgType.BRAND_LOGO) 
								&& imgObj.isActive())
						.findFirst()
						.get();						
	}
	
	/* Return object containing small logo img (in bytes form) */
	public CarBrandImg getSmallLogoImg() {		
		return this.carBrandImgSet.stream()
						.filter(imgObj -> imgObj.getImgType().equals(CarBrandImg.ImgType.BRAND_LOGO_SMALL)
								&& imgObj.isActive())
						.findFirst()
						.get();						
	}
	
	/* Getters, Setters*/
	public String getName() {		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescShort() {
		return descShort;
	}

	public void setDescShort(String descShort) {
		this.descShort = descShort;
	}

	public String getDescLong() {
		return descLong;
	}

	public void setDescLong(String descLong) {
		this.descLong = descLong;
	}

	public long getId() {
		return id;
	}



	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<CarBrandImg> getCarBrandImgSet() {
		return carBrandImgSet;
	}

	public void setCarBrandImgSet(Set<CarBrandImg> carBrandImgSet) {
		this.carBrandImgSet = carBrandImgSet;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
