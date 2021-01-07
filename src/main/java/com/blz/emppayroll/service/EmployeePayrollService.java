package com.blz.emppayroll.service;

import java.util.ArrayList;
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
	
	private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
	
	public List<EmployeePayrollData> getEmployeePayrollData() {
		return employeePayrollList;
	}

	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeePayrollList.stream()
					.filter(empData -> empData.getEmployeeId() == empId)
					.findFirst()
					.orElseThrow(() -> new EmployeePayrollException("Employee Not Found"));
	}

	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = null;
		empData = new EmployeePayrollData(empPayrollDTO);
		employeePayrollList.add(empData);
		log.debug("Emp Data: "+empData.toString());
		return employeeRepository.save(empData);
	}

	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		empData.setName(empPayrollDTO.name);
		empData.setSalary(empPayrollDTO.salary);
		employeePayrollList.set(empId-1, empData);
		return empData;
	}

	public void deleteEmployeePayrollData(int empId) {
		employeePayrollList.remove(empId-1);
	}
}
