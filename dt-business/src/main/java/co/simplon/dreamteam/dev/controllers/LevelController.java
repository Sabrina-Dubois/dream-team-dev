package co.simplon.dreamteam.dev.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dreamteam.dev.dtos.out.LevelView;
import co.simplon.dreamteam.dev.services.LevelService;

@RequestMapping("/levels")
@RestController
public class LevelController {

	private LevelService levelService;

	public LevelController(LevelService levelService) {
		this.levelService = levelService;
	}

	@GetMapping
	public ResponseEntity<List<LevelView>> getLevels() {
		List<LevelView> levels = levelService.getAllLevels();
		return ResponseEntity.ok().body(levels);
	}
}
