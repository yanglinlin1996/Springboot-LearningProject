package com.linlinyang.restservices.springbootbuildingblocks.controllers;

import java.util.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.linlinyang.restservices.springbootbuildingblocks.entities.User;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserExistsException;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNameNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.services.UserService;

// Controller -
@Api(tags="User Management RESTful Services", value="UserController", description="Controller for User Management Service")
@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {

	// Autowire the UserService
	
	@Autowired
	private UserService userService;
	
	// getAllUsers Method
	@ApiOperation(value="Retrieve list of users")
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	// createUser Method
	// @RequestBody Annotation
	// @PostMapping Annotation
	@ApiOperation(value="Create a new user")
	@PostMapping
	public ResponseEntity<Void> createUser(@ApiParam("User information for a new user to be created.") @Valid @RequestBody User user, UriComponentsBuilder builder) {
		
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
	}
	
	// getUserById Method
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		
		try {
			Optional<User> userOptional = userService.getUserById(id);
			return userOptional.get();
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	// updateUserById Method
	@PutMapping("/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable("id") Long id) {
		
		try {
			return userService.updateUserById(user, id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
	}
	
	// deleteUserById Method
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	// getUserByUsername Method
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UserNameNotFoundException("Username: '" + username + "' not found in User repository");
		}
		return user;
	}
}
