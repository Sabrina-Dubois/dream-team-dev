package co.simplon.dtbusiness.controllers;

import java.util.List;

import co.simplon.dtbusiness.dtos.in.DeveloperUpdate;
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

    @PatchMapping("/{id}")
    public void updateDeveloper(@PathVariable final Long id, @ModelAttribute  final DeveloperUpdate developer) {
        service.updateDeveloper(id,developer);
    }

}
