package mirosimo.car_showroom2.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/*
 * This class represent composite key
 * - extra column is needed in connection table between User and Role entity 
 * - (relation M : N)
 * - I test the solution which I found on internet for this situation. 
 * 
 * */

@Embeddable
public class UserRoleId implements Serializable {
	
	private User user;
	private Role role;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
