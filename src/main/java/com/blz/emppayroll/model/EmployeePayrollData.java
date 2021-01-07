package com.blz.emppayroll.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.blz.emppayroll.dto.EmployeePayrollDTO;

import lombok.Data;

@Entity
@Table(name = "employee_payroll")
public @Data class EmployeePayrollData {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "salary")
	private long salary;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "profile_pic")
	private String profilePic;
	
	@ElementCollection
	@CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "department")
	private List<String> department;
	
	public EmployeePayrollData() {}
	
	public EmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		this.updateEmployeePayrollData(empPayrollDTO);
	}

	
	private void updateEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		this.name = empPayrollDTO.name;
		this.salary = empPayrollDTO.salary;
		this.gender = empPayrollDTO.gender;
		this.startDate = empPayrollDTO.startDate;
		this.note = empPayrollDTO.note;
		this.profilePic = empPayrollDTO.profilePic;
		this.department = empPayrollDTO.department;
	}
}
