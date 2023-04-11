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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/*
 * Relation between Car Model and Equipment Pack
 * 1 : N
 * Each Equipment pack can appear in just one Car Model.
 * Each Car Model could have more Equipments Packs
 * 
 * Relation between Engine and Equipment Pack
 * M : N  
 * Each equipments pack could have multiple types of engines.
 * And Each Engine can appear in more equipments pack.
 * 
 * Some types of engines available in one equipmant pack couldn't be available
 * in another equipment pack.
 * e.g. In basic equipment pack couldn't be possible for customer to order 
 *   the most powerfull engine.
 *   
 * 	       1 : N        1 : N                M : N
 * CarBrand --> CarModel --> CarEquipmentPack --> CarEngine
 * 
 * 
 * */
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
	@Column(name = "ID")
	private long id;
	
	@Size(min = 2, max = 50, message = "{app.validation.chars-count2-50}")
	@Pattern(regexp="^[.-ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓa-zA-Z0-9\s]{2,50}",message="{app.validation.chars}")
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_car_equipment_pack_id", referencedColumnName = "id")
	Set<CarEquipmentPackImg> carEquipmentPackImgs = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="f_car_model_id")
	CarModel carModel;
	
	
	
	@OneToMany(mappedBy = "primaryKey.carEquipmentPack",
            cascade = CascadeType.ALL)
	private Set<CarEquipmentPackCarEngine> carEquipmentPackCarEngines = new HashSet<>();
	
	public void addEngine(CarEquipmentPackCarEngine engine) {
		this.carEquipmentPackCarEngines.add(engine);
	}

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



	public void setId(long id) {
		this.id = id;
	}

	public void setCarEquipmentPackImgs(Set<CarEquipmentPackImg> carEquipmentPackImgs) {
		this.carEquipmentPackImgs = carEquipmentPackImgs;
	}

	public Set<CarEquipmentPackCarEngine> getCarEquipmentPackCarEngines() {
		return carEquipmentPackCarEngines;
	}

	public void setCarEquipmentPackCarEngines(Set<CarEquipmentPackCarEngine> carEquipmentPackCarEngines) {
		this.carEquipmentPackCarEngines = carEquipmentPackCarEngines;
	}
}
