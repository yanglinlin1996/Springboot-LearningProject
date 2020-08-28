package com.linlinyang.restservices.springbootbuildingblocks.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linlinyang.restservices.springbootbuildingblocks.entities.Order;
import com.linlinyang.restservices.springbootbuildingblocks.entities.User;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.OrderNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.linlinyang.restservices.springbootbuildingblocks.repositories.OrderRepository;
import com.linlinyang.restservices.springbootbuildingblocks.repositories.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	// getAllOrders for a user Method
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(userid);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		return optionalUser.get().getOrders();
	}
	
	// createOrder Method
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(userid);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		User user = optionalUser.get();
		order.setUser(user);
		return orderRepository.save(order);
		
	}
	
	// getOrderByOrderId Method
	@GetMapping("/{userid}/orders/{orderid}")
	public Optional<Order> getOrderByOrderId(@PathVariable Long userid, @PathVariable("orderid") Long orderid) throws UserNotFoundException, OrderNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(userid);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		Optional<Order> order = orderRepository.findById(orderid);
		if (!order.isPresent()) {
			throw new OrderNotFoundException("Order Not Found");
		}
		return order;
	}
	
	
	
	
	
	
	
	
}
