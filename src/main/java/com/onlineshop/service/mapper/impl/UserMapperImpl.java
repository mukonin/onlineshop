package com.onlineshop.service.mapper.impl;

import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.User;
import com.onlineshop.service.mapper.UserMapper;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class UserMapperImpl implements UserMapper {

  private ModelMapper modelMapper;

	@Autowired
	public UserMapperImpl(ModelMapper modelMapper){
		this.modelMapper = modelMapper;
		PropertyMap<UserRegistrationDTO, User> UserRegistrationDTOMap = new PropertyMap<UserRegistrationDTO, User>() {
			protected void configure() {
				map().setUsername(source.getUsername());
				map().setPassword(source.getPassword());
				map().setEmail(source.getEmail());
				skip().setRoles(null);
				skip().setId(null);
				skip().setStatus(null);
			}
		};
		modelMapper.addMappings(UserRegistrationDTOMap);
	}

	@Override
	public UserRegistrationDTO convertToDto(User user) {
		log.info("Entity User with user name: " + user.getUsername() + " convert to UserDto");
		UserRegistrationDTO userRegistrationDTO = modelMapper.map(user, UserRegistrationDTO.class);
		return userRegistrationDTO;
	}

	@Override
	public User convertToEntity(UserRegistrationDTO userRegistrationDTO) {
		log.info("UserDto with user name: " + userRegistrationDTO.getUsername() + " converted to Entity User");
		User user = modelMapper.map(userRegistrationDTO, User.class);
		return user;
	}
}
