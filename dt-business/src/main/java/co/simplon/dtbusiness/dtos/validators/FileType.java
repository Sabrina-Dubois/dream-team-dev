package co.simplon.dtbusiness.dtos.validators;

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
    String message() default "Merci de ne pas attache un image avec type jpn, jpec or gif ";

    String[] types() default { MediaType.ALL_VALUE };

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
