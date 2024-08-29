package co.simplon.dtbusiness.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dtbusiness.entities.ProfileEntity;
import co.simplon.dtbusiness.services.ProfilesService;

@RequestMapping("/profiles")
@RestController
public class ProfilesController {
    private ProfilesService service;

    public ProfilesController(ProfilesService service) {
	this.service = service;
    }

    @GetMapping
    public List<ProfileEntity> findAll() {
	return service.findAll();
    }

    @GetMapping("/{internalNumber}")
    public ProfileEntity findByInternalNumber(@PathVariable final String internalNumber) {
	return service.findByInternalNumber(internalNumber);
    }
}
