package com.onlineshop.service.impl;

import com.onlineshop.model.MyUserDetails;
import com.onlineshop.model.User;
import com.onlineshop.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by sanya on 06.07.2017.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private static Logger logger = LogManager.getLogger(UserDetailServiceImpl.class);

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("load user by username: " + username);
		User user = userService.getByUserame(username);
		if (user == null) throw new UsernameNotFoundException("user not found");
		return new MyUserDetails(user);
	}
}
