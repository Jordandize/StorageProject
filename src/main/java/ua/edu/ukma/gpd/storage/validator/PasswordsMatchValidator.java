package ua.edu.ukma.gpd.storage.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.edu.ukma.gpd.storage.annotation.PasswordsMatch;
import ua.edu.ukma.gpd.storage.dto.SignupFormDto;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

	@Override
    public void initialize(PasswordsMatch constraintAnnotation) { }
	
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {   
        SignupFormDto user = (SignupFormDto) object;
        return user.getPassword().equals(user.getPasswordRepeat());    
    }     
	
}
