package com.onlineshop.service;

import com.onlineshop.dto.UserDto;
import com.onlineshop.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Adam on 06.07.2017.
 */
@Transactional
public interface UserService {

	User getByUserName(String name);

	void changeStatus(long id);

	void updateUser(User user);

	void registerNewUserAccount(UserDto user);

	Boolean userNameExists(String userName);

	List<User> getByRole(String role);
}
