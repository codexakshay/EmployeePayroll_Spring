package com.blz.emppayroll.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blz.emppayroll.dto.EmployeePayrollDTO;
import com.blz.emppayroll.dto.ResponseDTO;
import com.blz.emppayroll.model.EmployeePayrollData;
import com.blz.emppayroll.service.IEmployeePayrollService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeePayrollController {
	
	@Autowired
	private IEmployeePayrollService employeePayrollService;
	
	@RequestMapping(value = {"", "/", "/get"})
	public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeePayrollService.getEmployeePayrollData();
		ResponseDTO respDTO = new ResponseDTO("GET call Successful", empDataList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/get/{empId}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
		EmployeePayrollData empData = null;
		empData = employeePayrollService.getEmployeePayrollDataById(empId);
		ResponseDTO respDTO = new ResponseDTO("GET Call For ID Successful", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/department/{department}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("department") String department) {
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeePayrollService.getEmployeesByDepartment(department);
		ResponseDTO respDTO = new ResponseDTO("Get Call For ID Successful", empDataList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
		log.debug("Employee payroll dto "+empPayrollDTO.toString());
		EmployeePayrollData empData = null;
		empData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data Successfully", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId, @Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = null;
		empData = employeePayrollService.updateEmployeePayrollData(empId, empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
		employeePayrollService.deleteEmployeePayrollData(empId);
		ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Deleted id: "+empId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
}
