package com.mirosimo.car_showroom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class CarColor {
	@Id
	@SequenceGenerator(
			name = "car_color_sequence",
			sequenceName = "car_color_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "car_color_sequence"
	)
	private long id;
	
	private String colorMarketingName;
	private String colorHtmlCode;
	
	public String getColorMarketingName() {
		return colorMarketingName;
	}
	public void setColorMarketingName(String colorMarketingName) {
		this.colorMarketingName = colorMarketingName;
	}
	public String getColorHtmlCode() {
		return colorHtmlCode;
	}
	public void setColorHtmlCode(String colorHtmlCode) {
		this.colorHtmlCode = colorHtmlCode;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
