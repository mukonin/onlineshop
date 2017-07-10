package com.onlineshop.dto.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by sanya on 07.07.2017.
 */

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
	String message() default ("");

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
