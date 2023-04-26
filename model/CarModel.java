package mirosimo.car_showroom2.model;


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
	
	@Size(min = 2, max = 50, message = "{app.validation.chars-count2-50}")
	@Pattern(regexp="^[ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓa-zA-Z0-9\s]{2,50}",message="{app.validation.chars}")
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
	
	// Sometimes is needed specific order of models
	@Column(nullable = true)
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
