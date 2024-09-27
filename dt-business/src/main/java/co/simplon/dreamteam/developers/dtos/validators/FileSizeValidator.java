package co.simplon.dreamteam.developers.dtos.validators;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private long max;

    @Override
    public void initialize(FileSize constraintAnnotation) {
	long maxSize = constraintAnnotation.max();
	if (maxSize <= 0) {
	    throw new IllegalArgumentException(String.format("value must be positive: %s", maxSize));
	}
	max = maxSize;
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
	System.out.println("value=" + file);

	if (file == null) {
	    return true;
	}
	return file.getSize() <= max;
    }
}
