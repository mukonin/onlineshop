package com.onlineshop.service.impl;

import com.onlineshop.dao.RoleDAO;
import com.onlineshop.model.Role;
import com.onlineshop.service.RoleService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sanya on 04.07.2017.
 */
@Log4j
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO dao;

	@Override
	public void save(Role role) {
		try {
			log.info("save role: " + role);
			dao.save(role);
		} catch (Exception ex) {
			log.error("error save role: " + role, ex);
		}
	}

	@Override
	public Role getRoleByName(String name) {
		Role role = new Role();
		try {
			log.info("get role by name: " + name);
			role = dao.getRoleByName(name);
		} catch (Exception ex) {
			log.error("error role by name: " + name, ex);
		}
		return role;
	}
}
