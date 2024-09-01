package co.simplon.dtbusiness.dtos.validators;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypeValidator implements ConstraintValidator<FileType, MultipartFile> {
    private String[] types;

    @Override
    public void initialize(FileType constraintAnnotation) {
	types = constraintAnnotation.types();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}
	System.out.println(value.getContentType());
	System.out.println(types);

	final var type = value.getContentType();
	return Arrays.asList(types).contains(type);

    }

}