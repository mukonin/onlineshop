package com.onlineshop.service.impl;

import com.onlineshop.dao.RoleDao;
import com.onlineshop.dao.UserDao;
import com.onlineshop.dto.UserDto;
import com.onlineshop.model.Role;
import com.onlineshop.model.User;
import com.onlineshop.model.UserStatus;
import com.onlineshop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Adam on 06.07.2017.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	public User getByUserName(String name) {
		User user = new User();
		try {
			user = userDao.findUserByUserName(name);
		} catch (Exception e) {
			logger.error("error get user by user name:" + name, e);
		}
		return user;
	}

	public void changeStatus(long id) {
		try {
			User user = userDao.findUser(id);
			UserStatus userStatus = user.getStatus();

			if (userStatus == UserStatus.ACTIVE) {
				user.setStatus(UserStatus.INACTIVE);
				userDao.editUser(user);
			} else if (userStatus == UserStatus.INACTIVE) {
				user.setStatus(UserStatus.ACTIVE);
				userDao.editUser(user);
			}
		} catch (Exception e) {
			logger.error("error change status" + id, e);
		}
	}

	public void updateUser(User user) {
		userDao.editUser(user);
	}

	@Transactional
	public void registerNewUserAccount(UserDto userDto) {
		if (userNameExists(userDto.getUserName())) {
			logger.warn("user with name" + userDto.getUserName() + " exist");
		}
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleDao.findRoleByType("USER"));
		User user = new User();
		user.setStatus(UserStatus.ACTIVE);
		user.setNick_name(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		user.setRoles(roles);
		userDao.addUser(user);
	}

	public Boolean userNameExists(String userName) {
		return userDao.userNameExists(userName);
	}

	public List<User> getByRole(String role) {
		List<User> users = new ArrayList<User>();
		try {
			users = userDao.getByRole(role);
		} catch (Exception e) {
			logger.error("error get user by role:" + role, e);
		}
		return users;
	}
}
