package com.mirosimo.car_showroom.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private String urlName;
	private String descShort;
	private String descLong;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_car_brand_img_id", referencedColumnName = "id")
	private Set<CarBrandImg> carBrandImgSet = new HashSet<>();

	
	public CarBrandImg getBigLogoImg() {		
		return this.carBrandImgSet.stream()
						.filter(imgObj -> imgObj.getImgType().equals(CarBrandImg.ImgType.BRAND_LOGO))
						.findFirst()
						.get();						
	}
	
	public CarBrandImg getSmallLogoImg() {		
		return this.carBrandImgSet.stream()
						.filter(imgObj -> imgObj.getImgType().equals(CarBrandImg.ImgType.BRAND_LOGO_SMALL))
						.findFirst()
						.get();						
	}
	
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
	
	
	
}
