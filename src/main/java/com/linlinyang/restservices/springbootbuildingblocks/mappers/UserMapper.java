package com.linlinyang.restservices.springbootbuildingblocks.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.linlinyang.restservices.springbootbuildingblocks.dtos.UserMsDto;
import com.linlinyang.restservices.springbootbuildingblocks.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	// User to User DTO
	UserMsDto userToUserMsDto(User user);
	
	// List<User> to List<UserMsDto>
	List<UserMsDto> usersToUserDtos(List<User> users);
	
	
	
}
