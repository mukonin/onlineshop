package com.onlineshop.service.impl;


import com.onlineshop.dao.VerificationTokenDAO;
import com.onlineshop.model.VerificationToken;
import com.onlineshop.service.VerificationTokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	@Autowired
	private VerificationTokenDAO dao;

	@Override
	public VerificationToken findByToken(String token) {
		if (token == null || token.length() <= 0) {
			return null;
		}
		VerificationToken verificationToken = null;
		try {
			log.error("find verification token by token: " + token);
			verificationToken = dao.findByToken(token);
		} catch (Exception ex) {
			log.error("error to find verification token by token: " + token);
		}
		return verificationToken;
	}

	@Override
	public void deleteVerificationToken(VerificationToken token) {
		try {
			log.error("delete token: " + token);
			dao.delete(token);
		} catch (Exception ex) {
			log.error("error to delete token: " + token);
		}
	}

	@Override
	public void createVerificationToken(String token, String email) {
		try {
			log.info("create verification token by email: " + email);
			VerificationToken verificationToken = new VerificationToken(token, email);
			dao.save(verificationToken);
		} catch (Exception ex) {
			log.error("error to ");
		}
	}
}
