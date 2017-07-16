package com.onlineshop.dao;

import com.onlineshop.model.VerificationToken;
import org.springframework.stereotype.Component;


@Component
public interface VerificationTokenDAO extends GenericDAO<VerificationToken, Long> {
	VerificationToken findByToken(String token);
}
