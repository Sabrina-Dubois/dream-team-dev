package co.simplon.dtbusiness.services;

import java.util.List;

import co.simplon.dtbusiness.dtos.in.DeveloperUpdate;
import co.simplon.dtbusiness.dtos.in.SkillCreateOrAdd;
import co.simplon.dtbusiness.dtos.out.DevProfileBriefView;
import co.simplon.dtbusiness.dtos.out.DeveloperView;
import co.simplon.dtbusiness.entities.Skill;

public interface DeveloperService {
    DeveloperView findProjectedByInternalNumber(String internalNumber);

    List<DevProfileBriefView> findAllDevProfiles();

    void updateDeveloper(String internalNumber, DeveloperUpdate developer, List<SkillCreateOrAdd> skillsJson);

    Skill saveSkill(Skill skill);

    List<DevProfileBriefView> findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(String firstName,
	    String lastName, String technicalTopic, String level);
}
