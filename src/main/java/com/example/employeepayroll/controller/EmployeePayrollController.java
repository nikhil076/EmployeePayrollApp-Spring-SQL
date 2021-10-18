package com.example.employeepayroll.controller;

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

import com.example.employeepayroll.dto.EmployeePayrollDTO;
import com.example.employeepayroll.dto.ResponseDTO;
import com.example.employeepayroll.model.EmployeePayrollData;
import com.example.employeepayroll.services.IEmployeePayrollService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeePayrollController {
	
	@Autowired
	private IEmployeePayrollService employeePayrollService;

	/**
	 * Ability to fetch all employee details from Employee Payroll
	 * @return respDTO
	 */
	
	@RequestMapping(value = {"","/","/get"})
	public ResponseEntity<ResponseDTO> getEmployeeData(){
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeePayrollService.getEmployeePayrollData();
		ResponseDTO respDTO = new ResponseDTO("Get call successfull", empDataList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Ability to fetch employee details from Employee Payroll using ID
	 * @param empid
	 * @return respDTO
	 */
	@GetMapping("/get/{empid}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empid") int empid){
		EmployeePayrollData empData = null;
		empData = employeePayrollService.getEmployeePayrollDataById(empid);
		ResponseDTO respDTO = new ResponseDTO("get call successfull for id", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Ability to get employee from DB using department name
	 * @param department
	 * @return respDTO
	 */
	@GetMapping("/department/{department}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("department") String department){
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeePayrollService.getEmployeesByDepartment(department);
		ResponseDTO respDTO = new ResponseDTO("Get call for ID successfull", empDataList);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Ability to add employee details in Employee Payroll
	 * @param empPayrollDTO
	 * @return respDTO
	 */
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO){
		log.info("Employee DTO :"+empPayrollDTO.toString());
		EmployeePayrollData empData = null;
		empData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Create Employee successfull", empData);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	/**
	 * Ability to update employee details in Employee Payroll using ID
	 * @param empId
	 * @param empPayrollDTO
	 * @return respDTO
	 */
	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployeeData(@PathVariable("empId") int empId,@Valid @RequestBody EmployeePayrollDTO empPayrollDTO){
		EmployeePayrollData empData = null;
		empData = employeePayrollService.updateEmployeePayrollData(empId,empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("update employee payroll data successfull", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Ability to delete employee details from Employee Payroll using ID
	 * @param empid
	 * @return respDTO
	 */
	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empid") int empid){
		employeePayrollService.deleteEmployeePayrollData(empid);
		ResponseDTO respDTO = new ResponseDTO("Delete Successfull", "Deleted id : "+empid);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
}
