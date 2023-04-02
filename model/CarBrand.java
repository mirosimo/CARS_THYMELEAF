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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*
 * Represent Car Brand e.g. Skoda, Ford, Renault,... 
 * Car Brand is root of hierarchy
 * 
 * 		  1 : N        1 : N		          M	: N
 * CarBrand --> CarModel --> CarEquipmentPack --> CarEngine
 * 
 * */
@Table
@Entity
public class CarBrand {
	
	public CarBrand() {
		this.active = true;
	}
	
	
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
	
	@Size(min = 2, max = 50, message = "{app.validation.chars-count2-50}")
	@Pattern(regexp="^[ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓa-zA-Z0-9\s]{2,50}",message="{app.validation.chars}")
	private String name;
	
	// name used in url (without diacritic)
	@Size(min = 2, max = 50, message = "{app.validation.chars-count2-50}")
	@Pattern(regexp="^[a-zA-Z0-9]{2,50}",message="{app.validation.url-chars}")
	private String urlName;
	
	@Size(min = 2, max = 150, message = "{app.validation.chars-count2-150}")
	@Pattern(regexp="^[ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓa-zA-Z0-9\s]{2,150}",message="{app.validation.chars}")
	private String descShort;
	
	@Size(min = 2, max = 300, message = "{app.validation.chars-count2-300}")
	@Pattern(regexp="^[ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓa-zA-Z0-9\s]{2,300}",message="{app.validation.chars}")
	private String descLong;
	
	private boolean active;
			
	/* 
	 * This set contains image objects related to  particular car Brand 
	 * Big, Small Brand logo, etc...
	 * 
	 * */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_car_brand_img_id", referencedColumnName = "id")
	private Set<CarBrandImg> carBrandImgSet = new HashSet<>();
	
	@OneToMany(mappedBy="carBrand")
	private Set<CarEngine> carEngines;

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

	public Set<CarEngine> getCarEngines() {
		return carEngines;
	}

	public void setCarEngines(Set<CarEngine> carEngines) {
		this.carEngines = carEngines;
	}
}
