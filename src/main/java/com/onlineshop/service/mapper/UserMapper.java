package com.onlineshop.service.mapper;

import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.User;

/**
 * Created by Adam on 13.07.2017.
 */
public interface UserMapper {
	public UserRegistrationDTO convertToDto(User user);
	public User convertToEntity (UserRegistrationDTO userRegistrationDTO);
}
