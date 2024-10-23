package co.simplon.dreamteam.dev.dtos.out;

import java.util.Collection;

import jakarta.validation.constraints.Size;

public record DeveloperView(String internalNumber, String firstName, String lastName, String email,
		@Size(max = 10) String description, String picture, String linkedin, Collection<SkillsView> skills) {

}
