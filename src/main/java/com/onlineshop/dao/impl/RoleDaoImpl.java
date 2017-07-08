package com.onlineshop.dao.impl;

import com.onlineshop.dao.RoleDao;
import com.onlineshop.model.Role;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adam on 06.07.2017.
 */
@Repository
public class RoleDaoImpl  implements RoleDao{

	@Autowired
	SessionFactory session;

	public void addRole(Role role) {
		session.getCurrentSession().save(role);
	}

	public void editUser(Role role) {
		session.getCurrentSession().update(role);
	}

	public void deleteRole(Long roleId) {
		session.getCurrentSession().delete(findRole(roleId));
	}

	public Role findRole(Long roleId) {
		Query<Role> query = session.getCurrentSession().createQuery("from Role r where r.id =:id");
		query.setParameter("id",roleId);
		return query.uniqueResult();
	}

	public Role findRoleByType(String type) {
		Query<Role> query = session.getCurrentSession().createQuery("from Role r where r.name =:t");
		query.setParameter("t",type);
		return query.uniqueResult();
	}

	public List getAllRoles() {
		return session.getCurrentSession().createQuery("from Role").list();
	}
}
