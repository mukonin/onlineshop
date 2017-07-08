package com.onlineshop.service.impl;

import com.onlineshop.dao.UserDAO;
import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.User;
import com.onlineshop.service.RoleService;
import com.onlineshop.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by sanya on 04.07.2017.
 */
@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Override
	public void save(User user) {
		try {
			logger.info("save user: " + user);
			dao.save(user);
		} catch (Exception ex) {
			logger.error("error save user: " + user, ex);
		}
	}

	@Override
	public User getById(Long id) {
		User user = new User();
		try {
			logger.info("get user by id: " + id);
			user = dao.getById(id);
		} catch (Exception ex) {
			logger.error("error get user by id: " + id, ex);
		}
		return user;
	}

	@Override
	public void registrUser(UserRegistrationDTO userDTO) {
		try {
			logger.info("regist new user: " + userDTO);
			User user = new User();
			user.getRoles().add(roleService.getRoleByName("ROLE_USER"));
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setEmail(userDTO.getEmail());
			user.setUsername(userDTO.getUsername());
			save(user);
		} catch (Exception ex) {
			logger.error("error regist user: " + userDTO, ex);
		}
	}

	@Override
	public User getByUserame(String username) {
		User user = new User();
		try {
			logger.info("get user by username: " + username);
			user = dao.getByUsername(username);
		} catch (Exception ex) {
			logger.error("error get user by username: " + username, ex);
		}
		return user;
	}

	@Override
	public User getByEmail(String email) {
		User user = new User();
		try {
			logger.info("get user by email: " + email);
			user = dao.getByEmail(email);
		} catch (Exception ex) {
			logger.error("error get user by email: " + email, ex);
		}
		return user;
	}
}
