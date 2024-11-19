package co.simplon.dreamteam.dev.dtos.validators;

import co.simplon.dreamteam.dev.dtos.out.FilterView;
import co.simplon.dreamteam.dev.repositories.FilterRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FilterUniqueValidator implements ConstraintValidator<FilterUnique, FilterView> {

	private final FilterRepository filters;

	public FilterUniqueValidator(FilterRepository filters) {
		this.filters = filters;
	}

	@Override
	public boolean isValid(FilterView inputs, ConstraintValidatorContext context) {
		String name = inputs.name();
		String keyword = inputs.keyword();
		String firstTopic = inputs.firstTopic();
		String secondTopic = inputs.secondTopic();

		// Vérification : au moins un critère
		if ((firstTopic == null || firstTopic.isBlank()) && (secondTopic == null || secondTopic.isBlank())
				&& (keyword == null || keyword.isBlank())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Au moins une compétence ou un mot-clé doit être présent.")
					.addConstraintViolation();
			return false;
		}

		// Vérification de l'unicité
		boolean exists = filters.existsByNameAndFirstTopicAndSecondTopicAndKeyword(inputs.name(), firstTopic,
				secondTopic, keyword);

		if (exists) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Un filtre avec ces critères existe déjà.")
					.addConstraintViolation();
			return false;
		}

		return true;
	}

}
