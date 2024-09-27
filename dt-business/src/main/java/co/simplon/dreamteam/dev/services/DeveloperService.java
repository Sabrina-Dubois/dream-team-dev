package co.simplon.dreamteam.developers.services;

import java.util.List;

import co.simplon.dreamteam.developers.dtos.in.UpdateDeveloper;
import co.simplon.dreamteam.developers.dtos.in.CreateOrAddSkill;
import co.simplon.dreamteam.developers.dtos.out.DeveloperBriefView;
import co.simplon.dreamteam.developers.dtos.out.DeveloperView;
import co.simplon.dreamteam.developers.entities.Skill;

public interface DeveloperService {
    DeveloperView findProjectedByInternalNumber(String internalNumber);

    List<DeveloperBriefView> findAllDeveloperBriefs();

    void updateDeveloper(String internalNumber, UpdateDeveloper developer, List<CreateOrAddSkill> skillsJson);

    Skill saveSkill(Skill skill);

    List<DeveloperBriefView> findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(String firstName,
	    String lastName);

}
