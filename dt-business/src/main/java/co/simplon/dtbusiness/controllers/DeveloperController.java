package co.simplon.dtbusiness.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dtbusiness.entities.Developer;
import co.simplon.dtbusiness.services.DeveloperService;

@RequestMapping("/profiles")
@RestController
public class DeveloperController {
    private DeveloperService service;

    public DeveloperController(DeveloperService service) {
	this.service = service;
    }

    @GetMapping
    public List<Developer> findAll() {
	return service.findAll();
    }

    @GetMapping("/{internalNumber}")
    public Developer findByInternalNumber(@PathVariable final String internalNumber) {
	return service.findByInternalNumber(internalNumber);
    }
}
