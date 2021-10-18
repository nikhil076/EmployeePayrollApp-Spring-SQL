package com.example.employeepayroll.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeepayroll.dto.EmployeePayrollDTO;
import com.example.employeepayroll.exceptions.EmployeePayrollException;
import com.example.employeepayroll.model.EmployeePayrollData;
import com.example.employeepayroll.repository.EmployeePayrollRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePayrollservice implements IEmployeePayrollService{

	@Autowired
	private EmployeePayrollRepository employeePayrollRepository;
	
	/**
	 * Ability to fetch all employee details from Employee Payroll
	 */
	@Override
	public List<EmployeePayrollData> getEmployeePayrollData() {
		return employeePayrollRepository.findAll();
	}

	/**
	 * Ability to fetch employee details from Employee Payroll using ID
	 */
	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeePayrollRepository.findById(empId).orElseThrow( () -> new EmployeePayrollException("Employee with employeeId" + empId +" does not exist"));
	}

	
	/**
	 * Ability to get employee details from Employee Payroll using department
	 */
	@Override
	public List<EmployeePayrollData> getEmployeesByDepartment(String department) {
		return employeePayrollRepository.findEmployeesByDepartment(department);
	}
	
	
	/**
	 * Ability to add employee details in Employee Payroll
	 */
	@Override
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = null;
		empData = new EmployeePayrollData(empPayrollDTO);
		log.debug("Emp Data: "+empData.toString());
		return employeePayrollRepository.save(empData);
	}

	
	/**
	 * Ability to update employee details in Employee Payroll using ID
	 */
	@Override
	public EmployeePayrollData updateEmployeePayrollData(int empId,EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		empData.updateEmployeePayrollData(empPayrollDTO);
		return employeePayrollRepository.save(empData);
	}

	
	/**
	 * Ability to delete employee details from Employee Payroll using ID
	 */
	@Override
	public void deleteEmployeePayrollData(int empId) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		employeePayrollRepository.delete(empData);
	}

}
