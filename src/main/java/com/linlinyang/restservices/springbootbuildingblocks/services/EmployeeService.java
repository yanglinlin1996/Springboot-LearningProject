package com.linlinyang.restservices.springbootbuildingblocks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlinyang.restservices.springbootbuildingblocks.entities.Employee;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.repositories.EmployeeRepository;

// Service
@Service
public class EmployeeService {

	// Autowire the EmployeeRepository
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// getUserById
	public Optional<Employee> getEmployeeById(Long id) throws UserNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if (!employee.isPresent()) {
			throw new UserNotFoundException("Employee not found in user repository");
		}
		
		return employee;
	}
}
