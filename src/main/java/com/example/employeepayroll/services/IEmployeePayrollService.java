package com.example.employeepayroll.services;

import java.util.List;

import com.example.employeepayroll.dto.EmployeePayrollDTO;
import com.example.employeepayroll.model.EmployeePayrollData;

public interface IEmployeePayrollService {

	List<EmployeePayrollData> getEmployeePayrollData();
	EmployeePayrollData getEmployeePayrollDataById(int empId);
	EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO);
	EmployeePayrollData updateEmployeePayrollData(int empID,EmployeePayrollDTO empPayrollDTO);
	void deleteEmployeePayrollData(int empId);
}
