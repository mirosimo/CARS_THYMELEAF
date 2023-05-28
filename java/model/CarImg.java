package mirosimo.car_showroom2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table
@Entity
public class CarImg {
								
	@Id
	@SequenceGenerator(
			name = "car_img_sequence",
			sequenceName = "car_img_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "car_img_sequence"
	)
	private long id;
	
	/* Image type */
	private String imgType;
	
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
	
	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarImg entity = (CarImg) obj;
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
