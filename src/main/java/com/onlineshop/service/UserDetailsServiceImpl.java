package com.onlineshop.service;

import com.onlineshop.model.Role;
import com.onlineshop.model.User;
import com.onlineshop.model.UserStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 04.07.2017.
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	final static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


		User user = userService.getByUserName(s);

		if (user != null) {
			logger.info("User name: " + s);
			String password = user.getPassword();
			boolean enabled = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNotExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean credentialsNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonLocked = user.getStatus().equals(UserStatus.ACTIVE);


			org.springframework.security.core.userdetails.User userDetails =
					new org.springframework.security.core.userdetails.User(s, password, enabled, accountNotExpired,
							credentialsNonExpired, accountNonLocked, getGrantedAuthorities(user));

			return userDetails;
		} else {
			logger.error("UsernameNotFoundException");
			throw new UsernameNotFoundException("User not found!");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role userRole : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getName()));
		}
		return authorities;
	}


}

