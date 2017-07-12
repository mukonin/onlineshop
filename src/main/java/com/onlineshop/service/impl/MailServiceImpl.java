package com.onlineshop.service.impl;

import com.onlineshop.model.User;
import com.onlineshop.service.MailService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service
public class MailServiceImpl implements MailService {

	private static Logger logger = LogManager.getLogger(MailServiceImpl.class);

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendMail(User user) {
		MimeMessagePreparator preparator = getMessagePreparator(user);
		try{
			logger.info("Send mail to user");
			mailSender.send(preparator);
		}
		catch(Exception ex){
			logger.error("Error send mail", ex);
		}
	}

	private MimeMessagePreparator getMessagePreparator(User user){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom("accauntjava01@gmail.com");
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
				mimeMessage.setText("Dear " +  user.getUsername() + ", thank you for your registration in our online shop!");
				mimeMessage.setSubject("Registration in onlineshop");
			}
		};
		return preparator;
	}
}
