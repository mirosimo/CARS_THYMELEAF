package mirosimo.car_showroom2.model;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import javax.validation.constraints.Size;

import mirosimo.car_showroom2.custom_annotations.CzechChars;
import mirosimo.car_showroom2.custom_annotations.CzechChars1_50;
import mirosimo.car_showroom2.custom_annotations.EnChars1_50;


/*
 * Relation between Car Model and Equipment Pack is 1 : N
 * 
 * Each Equipment pack can appear in just one Car Model.
 * Each Car Model could have more Equipments Packs
 * 
 * Relation between CarEngine and CarEquipmentPack is M : N
 * 
 * Each equipmentPack (Style, Ambition, Sports pack, etc...)  
 * could offer multiple types of engines (1.4 MPI, 1.8 TSI, etc...).
 * And Each Engine can appear in 1 or more equipments packs.
 * 
 * Some types of engines available in one equipmant pack couldn't be available
 * in another equipment pack.
 * e.g. In basic equipment pack couldn't be possible for customer to order 
 *   the most powerfull engine.
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
	
	@CzechChars1_50
	private String name;
	
	// name used in url (without diacritic)
	@EnChars1_50
	private String urlName;
	
	@Size(min = 2, max = 150, message = "{app.validation.chars-count2-150}")
	@CzechChars
	private String descShort;
	
	@Size(min = 2, max = 300, message = "{app.validation.chars-count2-300}")
	@CzechChars
	private String descLong;
	
	private boolean active;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_car_equipment_pack_id", referencedColumnName = "id")
	Set<CarEquipmentPackImg> carEquipmentPackImgs = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="f_car_model_id")
	CarModel carModel;
	
	
	
	@OneToMany(mappedBy = "primaryKey.carEquipmentPack",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CarEquipmentPackCarEngine> carEquipmentPackCarEngines = new HashSet<>();
	
	public List<CarEquipmentPackCarEngine> getEnginesOrderedByEngineGroupAndName() {
		
		Comparator<CarEquipmentPackCarEngine> comparator = 
				Comparator.comparing((CarEquipmentPackCarEngine eqEngine) -> eqEngine.getCarEngine().getEngineGroup())
					.thenComparing((CarEquipmentPackCarEngine eqEngine) -> eqEngine.getCarEngine().getName());
											
		List<CarEquipmentPackCarEngine> ls = carEquipmentPackCarEngines.stream().collect(Collectors.toList());
		Collections.sort(ls, comparator);		
		return ls;		
	}

	
	/* getters and setters */
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarEquipmentPack entity = (CarEquipmentPack) obj;
		return id != 0L && id == entity.getId();		
	}
	
	/* Is used one number for all entities - one bucket for all entities 
	 * Reason why - ID is generated in database and therefore could 
	 * exist entities in transient state which don't have assigned ID yet */
	@Override
	public int hashCode() {
		return 23;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
}
