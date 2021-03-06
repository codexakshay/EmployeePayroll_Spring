package com.blz.emppayroll.service;

import java.util.List;

import com.blz.emppayroll.dto.EmployeePayrollDTO;
import com.blz.emppayroll.model.EmployeePayrollData;

public interface IEmployeePayrollService {

	List<EmployeePayrollData> getEmployeePayrollData();
	
	EmployeePayrollData getEmployeePayrollDataById(int empId);
	
	List<EmployeePayrollData> getEmployeesByDepartment(String department);
	
	EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO);
	
	EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO);
	
	void deleteEmployeePayrollData(int empId);
}
