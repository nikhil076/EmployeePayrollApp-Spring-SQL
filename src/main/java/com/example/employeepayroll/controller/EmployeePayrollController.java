package com.example.employeepayroll.controller;

import java.util.List;

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

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {
	
	@Autowired
	private IEmployeePayrollService employeePayrollService;

	@RequestMapping(value = {"","/","/get"})
	public ResponseEntity<ResponseDTO> getEmployeeData(){
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeePayrollService.getEmployeePayrollData();
		ResponseDTO respDTO = new ResponseDTO("Get call successfull", empDataList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/get/{empid}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empid") int empid){
		EmployeePayrollData empData = null;
		empData = employeePayrollService.getEmployeePayrollDataById(empid);
		ResponseDTO respDTO = new ResponseDTO("get call successfull for id", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> addEmployeePayrollData(@RequestBody EmployeePayrollDTO empPayrollDTO){
		EmployeePayrollData empData = null;
		empData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Create Employee successfull", empData);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployeeData(@PathVariable("empId") int empId, @RequestBody EmployeePayrollDTO empPayrollDTO){
		EmployeePayrollData empData = null;
		empData = employeePayrollService.updateEmployeePayrollData(empId,empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("update employee payroll data successfull", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empid") int empid){
		employeePayrollService.deleteEmployeePayrollData(empid);
		ResponseDTO respDTO = new ResponseDTO("Delete Successfull", "Deleted id : "+empid);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
}
