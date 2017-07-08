package com.onlineshop.dao;

import com.onlineshop.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adam on 04.07.2017.
 */
@Component
public interface UserDao {
	void addUser (User user);
	void editUser (User user);
	void deleteUser (Long usetId);
	Boolean userNameExists(String email);
	Boolean EmailExists(String email);
	User findUser (Long userId);
	User findUserByUserName(String userName);
	User findUserByEmail(String email);
	List getAllUsers();
	List<User> getByRole(String role);
}
