package mirosimo.car_showroom2.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import mirosimo.car_showroom2.enums.Degree;



@Entity
@Table
public class Employee {

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
	@Column(name = "EMPLOYEE_ID")
	private long id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Degree degree;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date employeementDate;
	
	@Email
	private String email;
	private String phone;	
	private String mobilePhone;		
	
	@ManyToOne
    @JoinColumn(name="f_department_id")
	private Department department;
	
	
	@ManyToOne
    @JoinColumn(name="f_work_position_id")
	private WorkPosition workPosition;
	
	@ManyToOne
    @JoinColumn(name="f_education_id")
	private Education education;
	
	/* 
	 * Each employee can visit more educational courses.
	 * And on the other hand the course could visit more employees. 
	 * Relation M:N - in connection table between Employee and Course
	 * are extra columns (courseStart, courseEnd, assessment).
	 * used Embedded class EmployeeCourseId for composite key. 
	 *  
	 * */
	@OneToMany(mappedBy = "primaryKey.employee",
            cascade = CascadeType.ALL)
	private Set<EmployeeCourse> employeeCourses = new HashSet<>();
	
	/* Employee has one or more addresses */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_employee_id", referencedColumnName = "employee_id")
	private Set<Address> addressSet = new HashSet<>();
	
	/* 
	 * Each employee can have zero or more photographs in system
	 * - relation 1 : N 
	 * */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "f_employee_id", referencedColumnName = "employee_id")
	private Set<EmployeeImg> employeeImgSet = new HashSet<>();
	
	
	public void addCourse(EmployeeCourse course) {
		this.employeeCourses.add(course);
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


	public void setId(long id) {
		this.id = id;
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




	public Set<Address> getAddressSet() {
		return addressSet;
	}


	public void setAddressSet(Set<Address> addressSet) {
		this.addressSet = addressSet;
	}


	public Set<EmployeeCourse> getEmployeeCourses() {
		return employeeCourses;
	}


	public void setEmployeeCourses(Set<EmployeeCourse> employeeCourses) {
		this.employeeCourses = employeeCourses;
	}


	public Set<EmployeeImg> getEmployeeImgSet() {
		return employeeImgSet;
	}


	public void setEmployeeImgSet(Set<EmployeeImg> employeeImgSet) {
		this.employeeImgSet = employeeImgSet;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee entity = (Employee) obj;
		return id != 0L && id == entity.getId();		
	}
	
	/* Is used one number for all entities - one bucket for all entities 
	 * Reason why - ID is generated in database and therefore could 
	 * exist entities in transient state which don't have assigned ID yet */
	@Override
	public int hashCode() {
		return 23;
	}
}
