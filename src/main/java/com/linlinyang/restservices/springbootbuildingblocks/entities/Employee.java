package com.linlinyang.restservices.springbootbuildingblocks.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

//Entity
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue
	@JsonView(Views.Normal.class)
	private Long empid;
	
	@NotEmpty(message="Employee name is Mandatory field. Please provide name")
	@Column(name="NAME", length=50, nullable=false)
	@JsonView(Views.Normal.class)
	private String name;
	
	@NotEmpty(message="Department is Mandatory field. Please provide department")
	@Column(name="DEPARTMENT", length=50, nullable=false)
	@JsonView(Views.Normal.class)
	private String department;
	
	@Column(name="LOGIN_TIME", length=50, nullable=false)
	@JsonView(Views.Manager.class)
	private Timestamp loginTime;
	
	@Column(name="LOGOUT_TIME", length=50, nullable=false)
	@JsonView(Views.Manager.class)
	private Timestamp logoutTime;
	
	@Column(name="SALARY", length=50, nullable=false)
	@JsonView(Views.HR.class)
	private Long salary;
	
	@Column(name="LAST_PROMO_DATE", length=50, nullable=false)
	@JsonView(Views.HR.class)
	private Date lastPromotionDate;

	// No Argument Constructor
	public Employee() {
		
	}

	// Fields Constructor
	public Employee(Long empid, String name, String department, Timestamp loginTime, 
			Timestamp logoutTime, Long salary, Date lastPromotionDate) {
		this.empid = empid;
		this.name = name;
		this.department = department;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.salary = salary;
		this.lastPromotionDate = lastPromotionDate;
	}

	// Getters and Setters
	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Date getLastPromotionDate() {
		return lastPromotionDate;
	}

	public void setLastPromotionDate(Date lastPromotionDate) {
		this.lastPromotionDate = lastPromotionDate;
	}

	// To String Method (optional for bean logging-in)
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", department=" + department + ", loginTime=" + loginTime
				+ ", logoutTime=" + logoutTime + ", salary=" + salary + ", lastPromotionDate=" + lastPromotionDate
				+ "]";
	}
	
	
}
