package com.linlinyang.restservices.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linlinyang.restservices.springbootbuildingblocks.entities.Order;
import com.linlinyang.restservices.springbootbuildingblocks.entities.User;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.repositories.OrderRepository;
import com.linlinyang.restservices.springbootbuildingblocks.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	// getAllOrders for a user Method
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(userid);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		List<Order> allorders = optionalUser.get().getOrders();
		@SuppressWarnings("deprecation")
		CollectionModel<Order> finalResources = new CollectionModel<Order>(allorders);
		return finalResources;
	}
}
