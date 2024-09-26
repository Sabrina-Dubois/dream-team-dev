package co.simplon.dtbusiness.controllers;

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

import co.simplon.dtbusiness.dtos.in.DeveloperUpdate;
import co.simplon.dtbusiness.dtos.in.SkillCreateOrAdd;
import co.simplon.dtbusiness.dtos.out.DevProfileBriefView;
import co.simplon.dtbusiness.dtos.out.DeveloperView;
import co.simplon.dtbusiness.services.DeveloperService;

@CrossOrigin("*")
@RequestMapping("/developers")
@RestController
public class DeveloperController {
    private DeveloperService service;

    public DeveloperController(DeveloperService service) {
	this.service = service;
    }

    @GetMapping
    public List<DevProfileBriefView> findAllDevProfiles() {
	return service.findAllDevProfiles();
    }

    @GetMapping("/{internalNumber}")
    public DeveloperView findByInternalNumber(@PathVariable final String internalNumber) {
	return service.findProjectedByInternalNumber(internalNumber);
    }

    @PatchMapping("/{internalNumber}")
    public void updateDeveloper(@PathVariable("internalNumber") String internalNumber,
	    @ModelAttribute final DeveloperUpdate developer, @RequestPart("skills") List<SkillCreateOrAdd> skillsJson) {
	service.updateDeveloper(internalNumber, developer, skillsJson);
    }

    @GetMapping("/search")
    public List<DevProfileBriefView> findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(
	    @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
	return service.findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(firstName, lastName);
    }

}
