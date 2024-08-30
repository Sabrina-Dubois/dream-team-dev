package co.simplon.dtbusiness.dtos.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = FileSizeValidator.class)
@Documented
public @interface FileSize {
    static final long ONE_MB = 1024 * 1024;

    static final long TWO_MB = 2 * ONE_MB;

    String message() default "Merci de ne pas attache un image moins 1MG";

    long max() default ONE_MB;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
