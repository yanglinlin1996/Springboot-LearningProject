package com.linlinyang.restservices.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.linlinyang.restservices.springbootbuildingblocks.entities.Order;
import com.linlinyang.restservices.springbootbuildingblocks.entities.User;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.repositories.UserRepository;
import com.linlinyang.restservices.springbootbuildingblocks.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	// getAllUsers Method
	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> allusers = userService.getAllUsers();
		
		for (User user: allusers) {
			// Self link
			Long userid = user.getId();
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			
			// Relationship link with all orders
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		Link selflinkGetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		@SuppressWarnings("deprecation")
		CollectionModel<User> finalResources = new CollectionModel<User>(allusers, selflinkGetAllUsers);
		return finalResources;
	}
	
	// getUserById Method
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
			
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getId();
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			@SuppressWarnings("deprecation")
			EntityModel<User> finalResource = new EntityModel<User>(user);
			return finalResource;
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
			
	}
	
	
}
