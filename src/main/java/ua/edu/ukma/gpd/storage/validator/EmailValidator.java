package ua.edu.ukma.gpd.storage.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.edu.ukma.gpd.storage.annotation.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {
	
	private Pattern pattern;
	
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void initialize(final Email constraintAnnotation) { }

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) {
		return validateEmail(email);
	}
	
	private boolean validateEmail(final String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
