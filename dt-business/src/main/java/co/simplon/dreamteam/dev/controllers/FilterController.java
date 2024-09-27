package co.simplon.dreamteam.developers.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dreamteam.developers.entities.Filter;

@RequestMapping("/filters")
@RestController
public class FilterController {

	@PostMapping("/create")
	public void createFilter(@RequestBody Filter filter) {
	}

}
