package co.simplon.dreamteam.dev.dtos.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME) //
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy = FilterUniqueValidator.class)
public @interface FilterUnique {
	String message() default " Merci de remplir les 3 champs";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
