package com.linlinyang.restservices.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import com.linlinyang.restservices.springbootbuildingblocks.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linlinyang.restservices.springbootbuildingblocks.dtos.UserMsDto;
import com.linlinyang.restservices.springbootbuildingblocks.mappers.UserMapper;
import com.linlinyang.restservices.springbootbuildingblocks.repositories.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
//@ComponentScan(basePackages = "com.linlinyang.restservices.springbootbuildingblocks.mappers")
public class UserMapStructController {

//	@Autowired
	private UserRepository userRepository;
//	
//	@Autowired
	private UserMapper userMapper;
	
	
	@Autowired
	public UserMapStructController(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}


	@GetMapping
	public List<UserMsDto> getAllUserDtos() {
		return userMapper.usersToUserDtos(userRepository.findAll());
	}

	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		User user = userOptional.get();
		return userMapper.userToUserMsDto(user);
	}
}
