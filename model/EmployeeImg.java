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
public class EmployeeImg {
	@Id
	@SequenceGenerator(
			name = "employee_img_sequence",
			sequenceName = "employee_img_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "employee_img_sequence"
	)
	private long id;
	
	@Lob
	private byte[] img;
	
	private String imgType;

	public byte[] getImg() {
		return img;
	}
	
	//@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@JoinColumn(name = "f_employee_img_id", referencedColumnName = "id")
	//private List<Employee> listEmployee = new ArrayList<>();
	
	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeImg entity = (EmployeeImg) obj;
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
