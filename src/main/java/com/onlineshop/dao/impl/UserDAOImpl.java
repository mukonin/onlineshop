package com.onlineshop.dao.impl;

import com.onlineshop.dao.UserDAO;
import com.onlineshop.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by sanya on 04.07.2017.
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {
	private static Logger logger = LogManager.getLogger(UserDAOImpl.class);

	@Override
	public User getByUsername(String username) {
		try {
			logger.info("getByUsername username: " + username);
			Query<User> query = getSession().createQuery("from User u where u.username = :username");
			query.setParameter("username", username);
			return query.uniqueResult();
		} catch (Exception ex) {
			logger.error("Error get user by username: " + username + ex);
			throw ex;
		}
	}

	@Override
	public User getByEmail(String email) {
		try {
			logger.info("getByEmail email: " + email);
			Query<User> query = getSession().createQuery("from User where email = :email");
			query.setParameter("email", email);
			return query.uniqueResult();
		} catch (Exception ex) {
			logger.error("Error get user by email: " + email + ex);
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
}
