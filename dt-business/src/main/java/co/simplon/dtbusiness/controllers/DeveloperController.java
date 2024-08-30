package co.simplon.dtbusiness.controllers;

import java.util.List;

import co.simplon.dtbusiness.dtos.in.DeveloperUpdate;
import co.simplon.dtbusiness.dtos.in.SkillCreateOrAdd;
import org.springframework.web.bind.annotation.*;

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
    public List<DeveloperView> findAll() {
        return service.findAll();
    }

    @GetMapping("/{internalNumber}")
    public DeveloperView findByInternalNumber(@PathVariable final String internalNumber) {
        return service.findProjectedByInternalNumber(internalNumber);
    }

    @PatchMapping("/{internalNumber}")
    public void updateDeveloper(@PathVariable("internalNumber") String internalNumber,
                                @ModelAttribute final DeveloperUpdate developer,
                                @RequestPart("skills") List<SkillCreateOrAdd> skillsJson) {
        service.updateDeveloper(internalNumber, developer, skillsJson);
    }


}
