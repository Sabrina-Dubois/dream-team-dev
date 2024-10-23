package co.simplon.dreamteam.dev.dtos.out;

import co.simplon.dreamteam.dev.dtos.validators.FilterUnique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@FilterUnique
public record FilterView(@NotBlank @Size(max = 50) String name,

		@Size(max = 20) String keyword,

		@NotNull String firstTopic,

		String secondTopic) {

}
