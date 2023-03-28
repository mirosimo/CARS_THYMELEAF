package com.mirosimo.car_showroom.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.user",
        joinColumns = @JoinColumn(name = "USER_ID")),
    @AssociationOverride(name = "primaryKey.role",
        joinColumns = @JoinColumn(name = "ROLE_ID")) })
public class UserRole {
	// comosite key
	private UserRoleId primaryKey = new UserRoleId();
		
	// additional field
	public int activated;
	
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
 
    public int getActivated() {
        return activated;
    }
 
    public void setActivated(int activated) {
        this.activated = activated;
    }
}
