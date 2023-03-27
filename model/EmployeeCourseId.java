package com.mirosimo.car_showroom.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/*
 * This class represent composite key
 * - EXTRA COLUMNS are needed in connection table between Employee and Course entity 
 * - (relation M : N) 
 * 
 * */

@Embeddable
public class EmployeeCourseId implements Serializable { 
	private Employee employee;
	private Course course;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	

}
