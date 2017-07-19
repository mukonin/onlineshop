package com.onlineshop.service;

import com.onlineshop.model.User;

/**
 * Created by sanya on 12.07.2017.
 */
public interface MailService {
	void sendMail(String toEmail, String message, String subject);
	void sendRegistrationMail(User user);
	void sendInviteManagerMail(String email, String token, String url);
}
