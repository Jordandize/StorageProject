package ua.edu.ukma.gpd.storage.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.edu.ukma.gpd.storage.validator.EmailValidator;

@Target    ({ TYPE, FIELD, ANNOTATION_TYPE }) 
@Retention (RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {
	
    String message() default "Invalid email";
    
    Class<?>                []  groups() default { }; 
    
    Class<? extends Payload>[] payload() default { };
    
}