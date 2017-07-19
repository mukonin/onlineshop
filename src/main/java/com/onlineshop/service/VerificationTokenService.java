package com.onlineshop.service;

import com.onlineshop.model.VerificationToken;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VerificationTokenService {
	VerificationToken findByToken(String token);
	void deleteVerificationToken(VerificationToken token);
	void createVerificationToken(String token, String email);
}
