package com.onlineshop.service.annotation;

import com.onlineshop.dao.UserDao;
import com.onlineshop.service.annotation.UniqueUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Adam on 07.07.2017.
 */
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

	@Autowired
	private UserDao userDAO;


	public void initialize(UniqueUserName constraintAnnotation) {	}


	@Transactional(readOnly = true)
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !userDAO.userNameExists(value);
	}
}
