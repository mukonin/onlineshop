package com.onlineshop.dao.impl;

import com.onlineshop.dao.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by sanya on 04.07.2017.
 */
abstract public class GenericDAOImpl<T, PK extends Serializable>  implements GenericDAO<T, PK>{
	public Class<T> type;

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		this.type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T instance) {
		getSession().save(instance);
	}

	@Override
	public void delete(T instance) {
		getSession().delete(instance);
	}

	@Override
	public void update(T instance) {
		getSession().update(instance);
	}

	@Override
	public T getById(PK id) {
		return  getSession().get(type, id);
	}
}
