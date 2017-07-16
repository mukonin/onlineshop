package com.onlineshop.service.impl;

import com.onlineshop.dao.UserDAO;
import com.onlineshop.dto.UserRegistrationDTO;
import com.onlineshop.model.User;
import com.onlineshop.model.UserStatus;
import com.onlineshop.service.MailService;
import com.onlineshop.service.RoleService;
import com.onlineshop.service.UserService;
import com.onlineshop.service.mapper.impl.UserMapperImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	@Autowired
	private MailService mailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserMapperImpl userMapper;

	@Override
	public void save(User user) {
		try {
			log.info("save user: " + user);
			dao.save(user);
		} catch (Exception ex) {
			log.error("error save user: " + user, ex);
		}
	}

	@Override
	public User getById(Long id) {
		User user = new User();
		try {
			log.info("get user by id: " + id);
			user = dao.getById(id);
		} catch (Exception ex) {
			log.error("error get user by id: " + id, ex);
		}
		return user;
	}

	@Override
	public void registrUser(UserRegistrationDTO userDTO) {
		try {
			log.info("regist new user: " + userDTO);
			User user = userMapper.convertToEntity(userDTO);
			user.getRoles().add(roleService.getRoleByName("ROLE_USER"));
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setStatus(UserStatus.ACTIVE);
			save(user);
			mailService.sendRegistrationMail(user);
		} catch (Exception ex) {
			log.error("error regist user: " + userDTO, ex);
		}
	}

	@Override
	public void registrManager(UserRegistrationDTO userDTO) {
		try {
			log.info("Regitr manager : " + userDTO);
			User user = userMapper.convertToEntity(userDTO);
			user.getRoles().add(roleService.getRoleByName("ROLE_USER"));
			user.getRoles().add(roleService.getRoleByName("ROLE_MANAGER"));
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setStatus(UserStatus.ACTIVE);
			save(user);
			mailService.sendRegistrationMail(user);
		} catch (Exception ex) {
			log.error("error regist manager: " + userDTO, ex);
		}
	}

	@Override
	public User getByUserame(String username) {
		User user = new User();
		try {
			log.info("get user by username: " + username);
			user = dao.getByUsername(username);
		} catch (Exception ex) {
			log.error("error get user by username: " + username, ex);
		}
		return user;
	}

	@Override
	public User getByEmail(String email) {
		User user = new User();
		try {
			log.info("get user by email: " + email);
			user = dao.getByEmail(email);
		} catch (Exception ex) {
			log.error("error get user by email: " + email, ex);
		}
		return user;
	}

	@Override
	public List<User> findByRole(Integer page, Integer perPage, String role) {
		try {
			log.info("find uers bu role: " + role);
			return dao.findByRole(page, perPage, role);
		} catch (Exception ex) {
			log.error("error find uers bu role: " + role, ex);
		}
		return null;
	}

	@Override
	public Long getCountByRole(String role) {
		try {
			return dao.getCountByRole(role);
		} catch (Exception ex) {
			log.error("error get count by role: " + role);
		}
		return null;
	}

	@Override
	public int pageCount(Long countItems, Integer perpage) {
		return (int) Math.ceil((double) countItems / perpage);
	}

	@Override
	public void setStatus(Long id, UserStatus status) {
		try {
			log.info("set status : " + status + ", id: " + id);
			User user = getById(id);
			if (!user.getStatus().equals(status)) {
				user.setStatus(status);
				dao.update(user);
			}
		} catch (Exception ex) {
			log.error("error to set status : " + status + ", id: " + id);
		}

	}
}
