package com.onlineshop.dao.impl;

import com.onlineshop.dao.RoleDAO;
import com.onlineshop.model.Role;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {
	private static Logger logger = LogManager.getLogger(RoleDAOImpl.class);

	@Override
	public Role getRoleByName(String name) {
		try {
			logger.info("getRoleByName name: " + name);
			Query<Role> query = getSession().createQuery("from Role r where r.name=:name");
			return query.setParameter("name", name).getSingleResult();
		} catch (Exception ex) {
			logger.error("Error get role by name: " + name + ex);
			throw ex;
		}
	}
}
