package co.simplon.dtbusiness.dtos.out;

import java.util.Collection;

public record DeveloperView(
        String internalNumber,
        String firstName,
        String lastName,
        String email,
        String description,
        String picture, String linkedin,
        Collection<SkillsView> skills) {

}
