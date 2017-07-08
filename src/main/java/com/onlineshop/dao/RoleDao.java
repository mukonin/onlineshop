package com.onlineshop.dao;

import com.onlineshop.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Adam on 06.07.2017.
 */
@Component
public interface RoleDao {
	void addRole(Role role);

	void editUser(Role role);

	void deleteRole(Long roleId);

	Role findRole(Long roleId);

	Role findRoleByType(String type);

	List getAllRoles();
}
