package com.onlineshop.service.mapper;

import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.User;

public interface UserMapper {
	public UserRegistrationDTO convertToDto(User user);
	public User convertToEntity (UserRegistrationDTO userRegistrationDTO);
}
