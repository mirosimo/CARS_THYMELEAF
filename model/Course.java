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

@Entity
@Table
public class Course {
	
	@Id
	@SequenceGenerator(
			name = "course_sequence",
			sequenceName = "course_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "course_sequence"
	)
	@Column(name = "COURSE_ID")
	private long id;
	
	@OneToMany(mappedBy = "primaryKey.course",
            cascade = CascadeType.ALL)
	private Set<EmployeeCourse> employeeCourses = new HashSet<EmployeeCourse>();
	
	private String courseName;
	private String courseDesc;
	private String company;
	
	
	
	@Override
	public int hashCode() {
	      return 200;
	}
	 
	@Override
	public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Course other = (Course) obj;
	        
	        //return id != null && id.equals(other.getId());
	        return id == other.getId();
	}
	
	public long getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}



	public void setId(long id) {
		this.id = id;
	}

	public Set<EmployeeCourse> getEmployeeCourses() {
		return employeeCourses;
	}

	public void setEmployeeCourses(Set<EmployeeCourse> employeeCourses) {
		this.employeeCourses = employeeCourses;
	}
}
