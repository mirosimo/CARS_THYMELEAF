package mirosimo.car_showroom2.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.employee",
        joinColumns = @JoinColumn(name = "EMPLOYEE_ID")),
    @AssociationOverride(name = "primaryKey.course",
        joinColumns = @JoinColumn(name = "COURSE_ID")) })
public class EmployeeCourse {
	// composite key
	EmployeeCourseId primaryKey = new EmployeeCourseId();
	
	// additional fields in connecting table between Employee x Course
	@Temporal(TemporalType.DATE)
	private Date courseBegin;
	
	@Temporal(TemporalType.DATE)
	private Date courseEnd;
	
	// Place where occur the course
	private String employeeCoursePlace;
	
	// Final assesment of employee
	private int assessment;
	
	@EmbeddedId	
	public EmployeeCourseId getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(EmployeeCourseId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Transient
	public Employee getEmployee() {
		return getPrimaryKey().getEmployee();
	}
	
	public void setEmployee(Employee employee) {
		getPrimaryKey().setEmployee(employee);
	}
	
	@Transient
	public Course getCourse() {
		return getPrimaryKey().getCourse();
	}
	
	public void setCourse(Course course) {
		getPrimaryKey().setCourse(course);
	}
	
	
	public Date getCourseBegin() {
		return courseBegin;
	}
	public void setCourseBegin(Date courseBegin) {
		this.courseBegin = courseBegin;
	}
	public Date getCourseEnd() {
		return courseEnd;
	}
	public void setCourseEnd(Date courseEnd) {
		this.courseEnd = courseEnd;
	}
	public int getAssessment() {
		return assessment;
	}
	public void setAssessment(int assessment) {
		this.assessment = assessment;
	}
	public String getEmployeeCoursePlace() {
		return employeeCoursePlace;
	}
	public void setEmployeeCoursePlace(String employeeCoursePlace) {
		this.employeeCoursePlace = employeeCoursePlace;
	}
}
