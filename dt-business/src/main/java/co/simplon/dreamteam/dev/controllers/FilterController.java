package co.simplon.dreamteam.dev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dreamteam.dev.dtos.out.FilterView;
import co.simplon.dreamteam.dev.services.FilterService;

@RequestMapping("/filters")
@RestController
public class FilterController {

	private final FilterService filterService;

	public FilterController(FilterService filterService) {
		this.filterService = filterService;
	}

	@PostMapping("/create")
	public ResponseEntity<Void> createFilter(@RequestBody FilterView filterView) {

		Long developerId = 1L;
		// filterService.createFilter(filterView, developerId);
		filterService.createFilter(filterView);
		return ResponseEntity.ok().build();
	}
}
