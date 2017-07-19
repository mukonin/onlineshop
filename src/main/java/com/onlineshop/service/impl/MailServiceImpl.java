package com.onlineshop.service.impl;

import com.onlineshop.model.User;
import com.onlineshop.service.MailService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Log4j
@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendInviteManagerMail(String email, String token, String url) {
		String message = "<p>Administrator of onlinae shop invited you to join a Managers team</p>" +
				"<p>Please follow the <a href='" + url + "/user/confirmmanager?token=" + token + "'>link</a> to complete your registration.</p>";
		String subject = "Invite to join a Managers team";
		sendMail(email, message, subject);
	}

	@Override
	public void sendRegistrationMail(User user) {
		String message = "Dear " + user.getUsername() + ", thank you for your registration in our online shop!";
		String subject = "Registration in onlineshop";
		sendMail(user.getEmail(), message, subject);
	}


	@Override
	public void sendMail(String toEmail, String message, String subject) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

		try {
			helper.setTo(toEmail);
			helper.setText(message, true);
			helper.setSubject(subject);
			helper.setFrom("accauntjava01@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			log.error("Can't send message", e);
		}
	}
}
