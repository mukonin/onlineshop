package com.onlineshop.dao.impl;

import com.onlineshop.dao.UserDao;
import com.onlineshop.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Adam on 04.07.2017.
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory session;


	public void addUser(User user) {
		session.getCurrentSession().save(user);
	}

	public void editUser(User user) {
		session.getCurrentSession().update(user);
	}

	public void deleteUser(Long usetId) {
		session.getCurrentSession().delete(findUser(usetId));
	}

	public Boolean userNameExists(String userName) {
		if (findUserByUserName(userName) == null) {
			return false;
		} else return true;
	}

	public Boolean EmailExists(String email) {
		if (findUserByEmail(email) == null) {
			return false;
		} else return true;
	}

	public User findUser(Long userId) {
		return session.getCurrentSession().get(User.class, userId);
	}


	@Transactional
	public User findUserByUserName(String userName) {
		Query<User> query = session.getCurrentSession().createQuery("from User u where u.nick_name = :name");
		query.setParameter("name", userName);
		User user = query.uniqueResult();
		return user;
	}

	public User findUserByEmail(String email) {
		Query<User> query = session.getCurrentSession().createQuery("from User u where u.email = :email");
		query.setParameter("email", email);
		User user = query.uniqueResult();
		return user;
	}

	public List<User> getAllUsers() {
		return session.getCurrentSession().createQuery("from User").list();
	}

	public List<User> getByRole(String role) {
		Query<User> query = session.getCurrentSession().createQuery("from User u where u.roles = :rol");
		query.setParameter("rol", role);
		return query.list();
	}
}
