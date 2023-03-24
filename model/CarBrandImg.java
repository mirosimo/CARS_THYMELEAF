package com.mirosimo.car_showroom.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table
@Entity
public class CarBrandImg {
	
	/* 
	 * Image types related with car brand 
	 * Sometimes are needed different types of logos, main image etc.
	 * */
	public enum ImgType {
		BRAND_IMG, BRAND_LOGO, BRAND_LOGO_SMALL
	} 
	
	@Id
	@SequenceGenerator(
			name = "car_brand_img_sequence",
			sequenceName = "car_brand_img_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "car_brand_img_sequence"
	)
	private long id;
	
	/* Image type related with car brand */
	@Enumerated(EnumType.STRING)
	private ImgType imgType;
	
	@Lob
	private byte[] img;
	
	/* Description which can be used e.g. in case of hover over an image */
	private String description;
	
	private boolean active;
	
	
	public CarBrandImg() {
		this.active = true;
	}
	
	// getters, setters

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ImgType getImgType() {
		return imgType;
	}

	public void setImgType(ImgType imgType) {
		this.imgType = imgType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
