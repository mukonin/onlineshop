package com.onlineshop.dao.impl;

import com.onlineshop.dao.UserDAO;
import com.onlineshop.model.User;
import lombok.extern.log4j.Log4j;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Log4j
@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

	@Override
	public User getByUsername(String username) {
		try {
			log.info("getByUsername username: " + username);
			Query<User> query = getSession().createQuery("from User u where u.username = :username");
			query.setParameter("username", username);
			return query.uniqueResult();
		} catch (Exception ex) {
			log.error("Error get user by username: " + username + ex);
			throw ex;
		}
	}

	@Override
	public User getByEmail(String email) {
		try {
			log.info("getByEmail email: " + email);
			Query<User> query = getSession().createQuery("from User where email = :email");
			query.setParameter("email", email);
			return query.uniqueResult();
		} catch (Exception ex) {
			log.error("Error get user by email: " + email + ex);
			throw ex;
		}
	}

	@Override
	public Boolean emailExists(String email) {
		return getByEmail(email) != null;
	}

	@Override
	public Boolean usernameExists(String username) {
		return getByUsername(username) != null;
	}

	@Override
	public List<User> findByRole(Integer page, Integer perPage, String role) {
		try {
			log.info("find users by role: " + role);
			Query<User> query = getSession().createQuery("SELECT u from User u JOIN u.roles r  where r.name = :p_role");
			query.setParameter("p_role", role);
			query.setFirstResult((page - 1) * perPage);
			query.setMaxResults(perPage);
			return query.list();
		} catch (Exception ex) {
			log.error("Error find users by role: " + role + ex);
			throw ex;
		}
	}

	@Override
	public Long getCountByRole(String role) {
		try {
			log.info("select count users by role: " + role);
			Query query = getSession().createQuery("SELECT count(*) from User u JOIN u.roles r  where r.name = :p_role");
			query.setParameter("p_role", role);
			return (Long) query.uniqueResult();
		} catch (Exception ex) {
			log.error("error select count users by role: " + role);
		}
		return null;
	}
}
