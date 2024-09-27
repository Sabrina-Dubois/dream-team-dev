package co.simplon.dreamteam.dev.services;

import java.util.List;

import co.simplon.dreamteam.dev.dtos.in.CreateSkill;
import co.simplon.dreamteam.dev.dtos.in.UpdateDeveloper;
import co.simplon.dreamteam.dev.dtos.out.DeveloperItemView;
import co.simplon.dreamteam.dev.dtos.out.DeveloperView;
import co.simplon.dreamteam.dev.entities.Skill;

public interface DeveloperService {
    DeveloperView findProjectedByInternalNumber(String internalNumber);

    List<DeveloperItemView> findAllDeveloperItems();

    void updateDeveloper(String internalNumber, UpdateDeveloper developer, List<CreateSkill> skillsJson);

    Skill saveSkill(Skill skill);

    List<DeveloperItemView> findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(String firstName,
	    String lastName, String technicalTopic, String level);

}
