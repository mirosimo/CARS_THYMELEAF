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
			
	public CarBrandImg() {
		this.active = true;
	}

	public CarBrandImg(ImgType imgType, byte[] img) {
		this.setImgType(imgType);
		this.setImg(img);
		this.setActive(true);
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarBrandImg entity = (CarBrandImg) obj;
		return id != 0L && id == entity.getId();		
	}
	
	/* Is used one number for all entities - one bucket for all entities 
	 * Reason why - ID is generated in database and therefore could 
	 * exist entities in transient state which don't have assigned ID yet */
	@Override
	public int hashCode() {
		return 23;
	}

}
