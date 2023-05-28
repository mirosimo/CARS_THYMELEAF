package mirosimo.car_showroom2.model;


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
	@Column(name="ID")
	private long id;
	
	
	/* 
	 * This field is here because of filter purposes.
	 * Available values could be ADMIN, SELLER, CUSTOMER, etc
	 * Than is possible filter roles related with value in this field 
	 * 
	 */	    	 
	private String roleType;
	
	private String name;
	
	private String desc_short;
	
	private String desc_long;
	
	private boolean active;
	
	@OneToMany(mappedBy = "primaryKey.role",
            cascade = CascadeType.ALL)
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	public Role() {		
	}
	
	public Role(String name) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role entity = (Role) obj;
		return id != 0L && id == entity.getId();		
	}
	
	/* Is used one number for all entities - one bucket for all entities 
	 * Reason why - ID is generated in database and therefore could 
	 * exist entities in transient state which don't have assigned ID yet */
	@Override
	public int hashCode() {
		return 23;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc_short() {
		return desc_short;
	}

	public void setDesc_short(String desc_short) {
		this.desc_short = desc_short;
	}

	public String getDesc_long() {
		return desc_long;
	}

	public void setDesc_long(String desc_long) {
		this.desc_long = desc_long;
	}
	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
