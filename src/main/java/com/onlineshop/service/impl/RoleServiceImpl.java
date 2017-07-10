package com.onlineshop.service.impl;

import com.onlineshop.dao.RoleDAO;
import com.onlineshop.model.Role;
import com.onlineshop.service.RoleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sanya on 04.07.2017.
 */
@Service
public class RoleServiceImpl implements RoleService {

	private static Logger logger = LogManager.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDAO dao;

	@Override
	public void save(Role role) {
		try {
			logger.info("save role: " + role);
			dao.save(role);
		} catch (Exception ex) {
			logger.error("error save role: " + role, ex);
		}
	}

	@Override
	public Role getRoleByName(String name) {
		Role role = new Role();
		try {
			logger.info("get role by name: " + name);
			role = dao.getRoleByName(name);
		} catch (Exception ex) {
			logger.error("error role by name: " + name, ex);
		}
		return role;
	}
}
