package mirosimo.car_showroom2.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
	
	@CzechChars1_50
	private String name;
	
	// name used in url (without diacritic)
	@EnChars1_50
	private String urlName;
	
	@CzechChars1_50
	private String descShort;
	
	@Size(min = 2, max = 300, message = "{app.validation.chars-count2-300}")
	@CzechChars
	private String descLong;
	
	// Sometimes is needed specific order of models
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarModel entity = (CarModel) obj;
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
