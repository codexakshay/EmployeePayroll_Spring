package com.blz.emppayroll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blz.emppayroll.dto.EmployeePayrollDTO;
import com.blz.emppayroll.exception.EmployeePayrollException;
import com.blz.emppayroll.model.EmployeePayrollData;
import com.blz.emppayroll.repository.EmployeePayrollRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService{

	@Autowired
	private EmployeePayrollRepository employeeRepository;
	
	public List<EmployeePayrollData> getEmployeePayrollData() {
		return employeeRepository.findAll();
	}

	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeeRepository
				.findById(empId)
				.orElseThrow(() -> new EmployeePayrollException("Employee with employeeId " + empId + " does not exists!!"));
	}

	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = null;
		empData = new EmployeePayrollData(empPayrollDTO);
		log.debug("Emp Data: "+empData.toString());
		return employeeRepository.save(empData);
	}

	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		empData.updateEmployeePayrollData(empPayrollDTO);
		return employeeRepository.save(empData);
	}

	public void deleteEmployeePayrollData(int empId) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		employeeRepository.delete(empData);
	}
}
