package com.onlineshop.service.annotation;
import com.onlineshop.service.annotation.PasswordMatch;
import org.apache.commons.beanutils.BeanUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Adam on 07.07.2017.
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

	private String firstFieldName;
	private String secondFieldName;
	private String message;


	public void initialize(final PasswordMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}


	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean toReturn = false;
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
			toReturn = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
		} catch (final Exception e) {
			System.out.println(e.toString());
		}
		if (!toReturn) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addNode(firstFieldName).addConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addNode(secondFieldName).addConstraintViolation();
		}
		return toReturn;
	}
}
