package com.mirosimo.car_showroom.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_employee_img_id", referencedColumnName = "id")
	private List<Employee> listEmployee = new ArrayList<>();
	
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

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
}
