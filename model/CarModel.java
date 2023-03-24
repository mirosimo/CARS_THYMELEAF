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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class CarModel {
	@Id
	@SequenceGenerator(
			name = "car_model_sequence",
			sequenceName = "car_model_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "car_model_sequence"
	)
	private long id;
	
	private String name;
	
	// String determing model name - used in url
	private String urlName;
	
	private String descShort;
	private String descLong;
	
	// Sometimes is needed specific order of models
	@Column(nullable = true)
	private int entityOrder;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "f_car_model_id", referencedColumnName = "id")
	Set<CarModelImg> carModelImgs = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="f_car_brand_id")
	CarBrand carBrand;
	
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

	

	public CarBrand getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(CarBrand carBrand) {
		this.carBrand = carBrand;
	}

	public long getId() {
		return id;
	}

	public Set<CarModelImg> getCarModelImgs() {
		return carModelImgs;
	}

	public void setCarModelImgs(Set<CarModelImg> carModelImgs) {
		this.carModelImgs = carModelImgs;
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

	public int getEntityOrder() {
		return entityOrder;
	}

	public void setEntityOrder(int entityOrder) {
		this.entityOrder = entityOrder;
	}


}
