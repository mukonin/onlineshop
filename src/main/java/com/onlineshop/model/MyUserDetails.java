package com.onlineshop.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by sanya on 06.07.2017.
 */
public class MyUserDetails extends User implements UserDetails {

	public MyUserDetails() {
		super();
	}

	public MyUserDetails(User user) {
		super(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRoles(), user.getStatus());
	}

	@Override
	public String getUsername(){
		return super.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return super.getRoles();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getStatus().equals(UserStatus.ACTIVE);
	}
}
