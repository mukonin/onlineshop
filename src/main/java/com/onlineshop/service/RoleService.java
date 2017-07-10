package com.onlineshop.service;

import com.onlineshop.model.Role;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sanya on 04.07.2017.
 */
@Transactional
public interface RoleService {
	void save(Role role);

	Role getRoleByName(String name);
}
