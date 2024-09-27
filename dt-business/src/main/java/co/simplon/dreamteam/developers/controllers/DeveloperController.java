package co.simplon.dreamteam.developers.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dreamteam.developers.dtos.in.UpdateDeveloper;
import co.simplon.dreamteam.developers.dtos.in.CreateOrAddSkill;
import co.simplon.dreamteam.developers.dtos.out.DeveloperBriefView;
import co.simplon.dreamteam.developers.dtos.out.DeveloperView;
import co.simplon.dreamteam.developers.services.DeveloperService;

@CrossOrigin("*")
@RequestMapping("/developers")
@RestController
public class DeveloperController {
    private DeveloperService service;

    public DeveloperController(DeveloperService service) {
	this.service = service;
    }

    @GetMapping
    public List<DeveloperBriefView> findAllDeveloperBriefs() {
	return service.findAllDeveloperBriefs();
    }

    @GetMapping("/{internalNumber}")
    public DeveloperView findByInternalNumber(@PathVariable final String internalNumber) {
	return service.findProjectedByInternalNumber(internalNumber);
    }

    @PatchMapping("/{internalNumber}")
    public void updateDeveloper(@PathVariable("internalNumber") String internalNumber,
	    @ModelAttribute final UpdateDeveloper developer, @RequestPart("skills") List<CreateOrAddSkill> skillsJson) {
	service.updateDeveloper(internalNumber, developer, skillsJson);
    }

    @GetMapping("/search")
    public List<DeveloperBriefView> findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(
	    @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
	return service.findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(firstName, lastName);
    }

}
