package co.simplon.dtbusiness.services;

import java.util.List;
import java.util.Set;

import co.simplon.dtbusiness.dtos.in.DeveloperUpdate;
import co.simplon.dtbusiness.dtos.in.SkillCreateOrAdd;
import co.simplon.dtbusiness.dtos.out.DeveloperView;
import co.simplon.dtbusiness.entities.Developer;

public interface DeveloperService {
    DeveloperView findProjectedByInternalNumber(String internalNumber);

    List<DeveloperView> findAll();

    void updateDeveloper(Long id, DeveloperUpdate developer);

}
