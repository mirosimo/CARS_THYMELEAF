package com.mirosimo.car_showroom.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * Car Engine e.g. Skoda, Ford, Renault,... 
 * Each equipments pack could have different offer of available engines.
 * (In basic equipment pack couldn't be possible for customer to order 
 *   the most powerfull engine)
 * 
 * 		  1 : N        1 : N		          1	: N
 * CarBrand --> CarModel --> CarEquipmentPack --> CarEngine
 * 
 * */
@Table
@Entity
public class CarEngine {		
	
	
	public enum EngineType {
		GAS, DIESEL, ELECTRIC
	}
	
	@Id
	@SequenceGenerator(
			name = "car_engine_sequence",
			sequenceName = "car_engine_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "car_engine_sequence"
	)
	
	private long id;
	
	@Enumerated(EnumType.STRING)
	private EngineType engineType;   // GAS, DIESEL, ELECTRIC	
	private String code;	
	private int power; 		// power in Kw
	private String name;
	private String nameMarketing;
	private int weight;		// weight in kg
	
	
	/* 
	 * Each engine could have set of its images... 
	 * */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_car_engine_id", referencedColumnName = "id")
	private Set<CarEngineImg> carEngineImgs = new HashSet<>();
	
	/* 
	 * One type of engine could appear in different equipment packs 
	 * And each equipment pack could have more engines. 	
	 * 
	 * */
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinTable(
				name="car_engine_equipmentpack",
				joinColumns = @JoinColumn(name = "engine_id"),
				inverseJoinColumns = @JoinColumn(name = "equipmentpack_id")
			)
	private Set<CarEquipmentPack> carEquipmentPacks = new HashSet<>();
	
	
	
	/* getters, setters*/
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameMarketing() {
		return nameMarketing;
	}

	public void setNameMarketing(String nameMarketing) {
		this.nameMarketing = nameMarketing;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Set<CarEngineImg> getCarEngineImgs() {
		return carEngineImgs;
	}	

	public long getId() {
		return id;
	}

	public Set<CarEquipmentPack> getCarEquipmentPacks() {
		return carEquipmentPacks;
	}

	public void setCarEquipmentPacks(Set<CarEquipmentPack> carEquipmentPacks) {
		this.carEquipmentPacks = carEquipmentPacks;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCarEngineImgs(Set<CarEngineImg> carEngineImgs) {
		this.carEngineImgs = carEngineImgs;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}
	
}
