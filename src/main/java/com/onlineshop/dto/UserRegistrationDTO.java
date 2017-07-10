package com.onlineshop.dto;

import com.onlineshop.dto.annotation.UniqueEmail;
import com.onlineshop.dto.annotation.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by sanya on 07.07.2017.
 */


@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRegistrationDTO {
	@Size(min = 6, max = 20)
	private String password;

	@NotEmpty
	@Email
	@UniqueEmail
	private String email;

	@Size(min = 3, max = 32)
	@UniqueUsername
	private String username;
}
