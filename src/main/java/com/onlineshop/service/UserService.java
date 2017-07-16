package com.onlineshop.service;

import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.User;
import com.onlineshop.model.UserStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sanya on 04.07.2017.
 */
@Transactional
public interface UserService {
	void save(User user);

	User getById(Long id);

	void registrUser(UserRegistrationDTO userDTO);

	void registrManager(UserRegistrationDTO userDTO);

	User getByUserame(String nickname);

	User getByEmail(String email);

	List<User> findByRole(Integer page, Integer perPage, String role);

	Long getCountByRole(String role);

	int pageCount(Long countItems, Integer perpage);

	void setStatus(Long id, UserStatus status);
}
