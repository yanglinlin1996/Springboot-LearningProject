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
import com.linlinyang.restservices.springbootbuildingblocks.entities.User;
import com.linlinyang.restservices.springbootbuildingblocks.entities.Views;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.services.UserService;

@RestController
@Validated
@RequestMapping(value="/jsonview/users")
public class UserJsonViewController {
	
	// Autowire the UserService
	@Autowired
	private UserService userService;

	// getUserById Method
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		
		try {
			return userService.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	// getUserById Method
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public Optional<User> getUserById2(@PathVariable("id") @Min(1) Long id) {
		
		try {
			return userService.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	
}
