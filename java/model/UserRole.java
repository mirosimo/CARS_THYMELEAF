package mirosimo.car_showroom2.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;





/*  
 * UserRole - Particular combination of User and Role - relation M:N
 *           - Contains primary key UserRoleId - composite key consist of Role and User
 *           - Represents connection entity -
 *    	
 *  Contains extra fields in connection entity 
 *  - this fields have sense just for one particular combination of User and Role
 *       - boolean active 		- Is role for user Active or Not 
 *       - Date dateAdded  		- Date when was role assigned to user
 *       - int whoAdd		 	- Who was assigned the role to user
 */ 

@Entity
@Table
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.user",
        joinColumns = @JoinColumn(name = "USER_ID")),
    @AssociationOverride(name = "primaryKey.role",
        joinColumns = @JoinColumn(name = "ROLE_ID")) })
public class UserRole {
	/*
	 *  Primary composite key
	 *  
	 */
	private UserRoleId primaryKey = new UserRoleId();
		
	/*
	 *  Additional fields 
	 *  Have sense just for one particular combination of keys 
	 *  
	 */
	
	/*
	 * Is role active or not
	 */
	private boolean active;
	
	/* 
	 * Date when was role added
	 * 
	 */
	private Date dateAdded;
	
	/*
	 * Id - who was assigned the role
	 * 
	 */
	private long whoAdd;
	
	
    @EmbeddedId
    public UserRoleId getPrimaryKey() {
        return primaryKey;
    }
    
    public void setPrimaryKey(UserRoleId primaryKey) {
        this.primaryKey = primaryKey;
    }
 
    @Transient
    public User getUser() {
        return getPrimaryKey().getUser();
    }
 
    public void setUser(User user) {
        getPrimaryKey().setUser(user);
    }
 
    @Transient
    public Role getRole() {
        return getPrimaryKey().getRole();
    }
 
    public void setRole(Role role) {
        getPrimaryKey().setRole(role);
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public long getWhoAdd() {
		return whoAdd;
	}

	public void setWhoAdd(long whoAdd) {
		this.whoAdd = whoAdd;
	}


}
