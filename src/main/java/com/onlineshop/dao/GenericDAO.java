package com.onlineshop.dao;

import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public interface GenericDAO<T, PK extends Serializable> {
	void save(T instance);

	void delete(T instance);

	void update(T instance);

	T getById(PK id);
}
