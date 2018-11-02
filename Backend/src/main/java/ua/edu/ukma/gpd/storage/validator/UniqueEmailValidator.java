package ua.edu.ukma.gpd.storage.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ua.edu.ukma.gpd.storage.annotation.UniqueEmail;
import ua.edu.ukma.gpd.storage.service.UserService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		try {
			return userService.getByEmail(email) == null ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
