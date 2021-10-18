package com.example.employeepayroll.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

public @ToString class EmployeePayrollDTO {

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name invalid")
	public String name;
	
	@Min(value = 500 , message = "Min wage should be more than 500")
	public long salary;
	
	@Pattern(regexp = "male|female", message="Gender needs to be male or femaale")
	public String gender;
	
	@JsonFormat(pattern = "dd MM YYYY")
	@NotNull(message = "StartDate should not be null")
	@PastOrPresent(message = "startDate should be past or todays date")
	public LocalDate startDate;
	
	@NotBlank(message = "Note cannot be empty")
	public String note;
	
	@NotBlank(message = "Profilepic cannot be empty")
	public String profilePic;
	
	@NotNull(message = "depaartment should not be null")
	public List<String> departments;
	
}
