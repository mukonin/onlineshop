package com.onlineshop.service.impl;

import com.onlineshop.dao.UserDAO;
import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.Role;
import com.onlineshop.model.User;
import com.onlineshop.model.UserStatus;
import com.onlineshop.service.MailService;
import com.onlineshop.service.RoleService;
import com.onlineshop.service.UserService;
import com.onlineshop.service.mapper.UserMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sanya on 04.07.2017.
 */
@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO dao;

	@Autowired
	private MailService mailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Autowired
	UserMapper userMapper;

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
			Set<Role> roleSet = new HashSet<>();
			roleSet.add(roleService.getRoleByName("ROLE_USER"));

			User user = userMapper.convertToEntity(userDTO);
			user.setRoles(roleSet);
			user.setStatus(UserStatus.ACTIVE);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			save(user);
			mailService.sendMail(user);
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
