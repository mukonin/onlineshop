package com.onlineshop.dao;

import com.onlineshop.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sanya on 04.07.2017.
 */
@Component
public interface UserDAO extends GenericDAO<User, Long> {
	User getByUsername(String username);

	User getByEmail(String email);

	Boolean emailExists(String email);

	Boolean usernameExists(String username);

	List<User> findByRole(Integer page, Integer perPage, String role);

	Long getCountByRole(String role);
}
