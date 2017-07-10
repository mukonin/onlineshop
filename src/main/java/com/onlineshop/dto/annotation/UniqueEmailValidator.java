package com.onlineshop.dto.annotation;

import com.onlineshop.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by sanya on 07.07.2017.
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void initialize(UniqueEmail uniqueEmail) {

	}

	@Override
	@Transactional(readOnly = true)
	public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
		return !userDAO.emailExists(email);
	}
}
