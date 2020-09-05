package com.linlinyang.restservices.springbootbuildingblocks.controllers;

import java.util.*;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.linlinyang.restservices.springbootbuildingblocks.entities.User;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.services.UserService;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
	
	// Autowire the UserService
	@Autowired
	private UserService userService;
	
	// getUserById Method - fields with HashSet
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			
			Set<String> fields = new HashSet<>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			fields.add("orders");
			
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields ));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			
			return mapper;
			
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	// getUserById Method - fields with @RequestParam
	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id,
			@RequestParam Set<String> fields) {
		
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields ));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			
			return mapper;
			
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
}
