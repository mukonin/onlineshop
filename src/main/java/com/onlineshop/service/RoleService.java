package com.onlineshop.service;

import com.onlineshop.model.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Adam on 06.07.2017.
 */
@Transactional
public interface RoleService {
	List<Role> getAll();
	Role getByType(String role);
	Role getById(long id);
}
