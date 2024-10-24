package co.simplon.dreamteam.dev.dtos.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = FileTypeValidator.class)
@Documented
public @interface FileType {
	String message() default "Merci d'envoyer une image avec type jpn, jpec ou gif ";

	String[] types() default { MediaType.ALL_VALUE };

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
