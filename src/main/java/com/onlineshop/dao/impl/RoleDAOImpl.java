package com.onlineshop.dao.impl;

import com.onlineshop.dao.RoleDAO;
import com.onlineshop.model.Role;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {

	@Override
	public Role getRoleByName(String name) {
		try {
			log.info("getRoleByName name: " + name);
			Query<Role> query = getSession().createQuery("from Role r where r.name=:name");
			return query.setParameter("name", name).getSingleResult();
		} catch (Exception ex) {
			log.error("Error get role by name: " + name + ex);
			throw ex;
		}
	}
}
