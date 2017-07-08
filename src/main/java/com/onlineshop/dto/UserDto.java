package com.onlineshop.dto;

import com.onlineshop.service.annotation.PasswordMatch;
import com.onlineshop.service.annotation.UniqueEmail;
import com.onlineshop.service.annotation.UniqueUserName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * Created by Adam on 07.07.2017.
 */
@Getter
@Setter
@PasswordMatch(first = "password", second = "confirmPassword", message = "The password fields must match!")
public class UserDto {

	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@NotEmpty(message = "Please enter your nick name!")
	@UniqueUserName(message = "User with this nick name is already exists!")
	@Size(min = 4, max = 20, message = "User name must be between 4 and 20 symbols!")
	private String userName;

	@NotEmpty(message = "Please enter your email!")
	@UniqueEmail(message = "User with this email is already exists1")
	@Pattern(regexp = EMAIL_PATTERN, message = "enter email in correct format!")
	private String email;

	@NotEmpty(message = "Please enter your password!")
	@Size(min = 6, max = 20, message = "User name must be between 6 and 20 symbols!")
	private String password;

	@NotEmpty(message = "Please enter your password again!")
	private String confirmPassword;

}