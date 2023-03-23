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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class CarEquipmentPack {
	@Id
	@SequenceGenerator(
			name = "car_equipment_pack_sequence",
			sequenceName = "car_equipment_pack_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "car_equipment_pack_sequence"
	)
	private long id;
	
	private String name;
	private String urlName;
	private String descShort;
	private String descLong;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "f_car_equipment_pack_id", referencedColumnName = "id")
	Set<CarEquipmentPackImg> carEquipmentPackImgs = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="f_car_model_id")
	CarModel carModel;
	
	
	
	@ManyToMany(mappedBy = "carEquipmentPacks",
	fetch = FetchType.EAGER, 
	cascade = {CascadeType.MERGE})
	private Set<CarEngine> carEngines = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
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

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public long getId() {
		return id;
	}

	public Set<CarEquipmentPackImg> getCarEquipmentPackImgs() {
		return carEquipmentPackImgs;
	}

	public Set<CarEngine> getCarEngines() {
		return carEngines;
	}

	public void setCarEngines(Set<CarEngine> carEngines) {
		this.carEngines = carEngines;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCarEquipmentPackImgs(Set<CarEquipmentPackImg> carEquipmentPackImgs) {
		this.carEquipmentPackImgs = carEquipmentPackImgs;
	}
}
