package com.mirosimo.car_showroom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table
@Entity
public class CarEquipmentPackImg {
	
	
	
	public CarEquipmentPackImg() {
		super();		
	}
	
	public CarEquipmentPackImg(byte[] imgData) {
		super();		
		this.setImg(imgData);
	}

	@Id
	@SequenceGenerator(
			name = "car_equipment_pack_img_sequence",
			sequenceName = "car_equipment_pack_img_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "car_equipment_pack_img_sequence"
	)
	private long id;
	
	private String imgType;
	
	@Lob
	private byte[] img;

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

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
	
}
