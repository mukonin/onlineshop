package com.onlineshop.service;

import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sanya on 04.07.2017.
 */
@Transactional
public interface UserService {
	void save(User user);

	User getById(Long id);

	void registrUser(UserRegistrationDTO userDTO);

	User getByUserame(String nickname);

	User getByEmail(String email);
}
