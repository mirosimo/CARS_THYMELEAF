package com.mirosimo.car_showroom.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Employee {
	public enum Degree {
		ING("Ing."), BC("Bc."), MUDR("Mudr."), DOC("Doc.");
		
		private final String niceValue;
		
		private Degree(String title) {
			this.niceValue = title;
		}
		
		public String getNiceValue() {
			return this.niceValue;
		}
	} 
	
	@Id
	@SequenceGenerator(
			name = "employee_sequence",
			sequenceName = "employee_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "employee_sequence"
	)
	private long id;
	
	//@ManyToMany(mappedBy = "listEmployees", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@ManyToMany(mappedBy = "listEmployees", fetch = FetchType.EAGER)
	private List<TrainingCourse> listCourses = new ArrayList<>();
	
	private String firstName;
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Degree degree;
	
	private Date birthdate;
	private Date employeementDate;
	private String email;
	private String phone;	
	private String mobilePhone;
	
	
	/* Employee has one or more addresses */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_employee_id", referencedColumnName = "id")
	private Set<Address> addressSet = new HashSet<>();

	
	@ManyToOne
    @JoinColumn(name="f_department_id")
	private Department department;
	
	
	@ManyToOne
    @JoinColumn(name="f_work_position_id")
	private WorkPosition workPosition;
	
	@ManyToOne
    @JoinColumn(name="f_education_id")
	private Education education;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="f_employee_img_id")
	private EmployeeImg employeeImg;
	
	public void assignCourse(TrainingCourse course) {
		this.listCourses.add(course);
	}
	
	
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
	        Employee other = (Employee) obj;
	        
	        return id == other.getId();
	}
	
	/* getters, setters */
	
	public long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<TrainingCourse> getListCourses() {
		return listCourses;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}





	public WorkPosition getWorkPosition() {
		return workPosition;
	}


	public void setWorkPosition(WorkPosition workPosition) {
		this.workPosition = workPosition;
	}


	public Education getEducation() {
		return education;
	}


	public void setEducation(Education education) {
		this.education = education;
	}


	public EmployeeImg getEmployeeImg() {
		return employeeImg;
	}


	public void setEmployeeImg(EmployeeImg employeeImg) {
		this.employeeImg = employeeImg;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setListCourses(List<TrainingCourse> listCourses) {
		this.listCourses = listCourses;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}


	public Date getEmployeementDate() {
		return employeementDate;
	}


	public void setEmployeementDate(Date employeementDate) {
		this.employeementDate = employeementDate;
	}


	public Degree getDegree() {
		return degree;
	}


	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	
	
}
