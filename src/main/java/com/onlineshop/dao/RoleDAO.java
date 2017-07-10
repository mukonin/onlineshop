package com.onlineshop.dao;


import com.onlineshop.model.Role;
import org.springframework.stereotype.Component;

/**
 * Created by sanya on 04.07.2017.
 */
@Component
public interface RoleDAO extends GenericDAO<Role, Long> {
	Role getRoleByName(String name);
}
