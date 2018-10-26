package ua.edu.ukma.gpd.storage.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.edu.ukma.gpd.storage.validator.PasswordsMatchValidator;


@Target    ({ TYPE, FIELD, ANNOTATION_TYPE }) 
@Retention (RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Documented
public @interface PasswordsMatch {
	
    String message() default "Passwords don't match";
    
    Class<?>                []  groups() default { }; 
    
    Class<? extends Payload>[] payload() default { };

}