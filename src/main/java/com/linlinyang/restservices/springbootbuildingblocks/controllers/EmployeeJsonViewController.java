package com.linlinyang.restservices.springbootbuildingblocks.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.linlinyang.restservices.springbootbuildingblocks.entities.Employee;
import com.linlinyang.restservices.springbootbuildingblocks.entities.Views;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.services.EmployeeService;

@RestController
@Validated
@RequestMapping(value="/jsonview/employee")
public class EmployeeJsonViewController {

	// Autowire the Employee Service
	@Autowired
	private EmployeeService empService;
	
	// getEmployeeById Method
	@JsonView(Views.Normal.class)
	@GetMapping("/normal/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") @Min(1) Long id) {
		
		try {
			return empService.getEmployeeById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	// getEmployeeById Method
	@JsonView(Views.Manager.class)
	@GetMapping("/manager/{id}")
	public Optional<Employee> getEmployeeById2(@PathVariable("id") @Min(1) Long id) {
		
		try {
			return empService.getEmployeeById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	// getEmployeeById Method
	@JsonView(Views.HR.class)
	@GetMapping("/hr/{id}")
	public Optional<Employee> getEmployeeById3(@PathVariable("id") @Min(1) Long id) {
		
		try {
			return empService.getEmployeeById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
}
