package com.onlineshop.service.impl;

import com.onlineshop.dao.RoleDao;
import com.onlineshop.model.Role;
import com.onlineshop.service.RoleService;
import com.onlineshop.service.UserDetailsServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 06.07.2017.
 */
@Service
public class RoleServiceImpl  implements RoleService{

	@Autowired
	RoleDao roleDao;

	final static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	public List<Role> getAll() {
		List<Role> roles = new ArrayList<Role>();
		try{
			 roles = roleDao.getAllRoles();
		}catch (Exception e){
			logger.error("error get all role", e);
		}
		return roles;
	}

	public Role getByType(String role) {
		Role role1 = new Role();
		try {
			role1= roleDao.findRoleByType(role);
		}catch (Exception e){
			logger.error("error get role: "+ role,e);
		}
		return role1;
	}

	public Role getById(long id) {
		Role role = new Role();
		try {
			role = roleDao.findRole(id);
		}catch (Exception e){
			logger.error("error ger role with id:"+id, e);
		}
		return role;
	}
}
