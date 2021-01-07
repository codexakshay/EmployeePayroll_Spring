package com.blz.emppayroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blz.emppayroll.model.EmployeePayrollData;

public interface EmployeePayrollRepository extends JpaRepository<EmployeePayrollData, Integer>{

}
