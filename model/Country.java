package com.mirosimo.car_showroom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class Country {
	@Id
	@SequenceGenerator(
			name = "country_sequence",
			sequenceName = "country_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "country_sequence"
	)
	private long id;	
	private String name;	
	private String countryCode;
	
	@Lob
	private byte[] flag;
	
	@OneToMany(mappedBy = "country")
	private Set<Address> addresses;
	
	public Country() {
		super();	
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_country_id", referencedColumnName = "id")
	private List<Employee> listEmployee = new ArrayList<>(); 

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public List<Employee> getListEmployee() {
		return listEmployee;
	}
	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	public byte[] getFlag() {
		return flag;
	}
	public void setFlag(byte[] flag) {
		this.flag = flag;
	}
}
