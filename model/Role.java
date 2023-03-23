package com.mirosimo.car_showroom.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table
@Entity
public class Role {
	@Id
	@SequenceGenerator(
			name = "role_sequence",
			sequenceName = "role_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "role_sequence"
	)
	@Column(name="ROLE_ID")
	private long id;
	private String name;
	
	@OneToMany(mappedBy = "primaryKey.role",
            cascade = CascadeType.ALL)
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	public Role() {		
	}
	
	public Role(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	public void addUserRole(UserRole userRole) {
		this.userRoles.add(userRole);
	}
	
	
}
