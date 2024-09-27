package co.simplon.dreamteam.developers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dreamteam.developers.dtos.out.LevelView;
import co.simplon.dreamteam.developers.dtos.out.TopicView;
import co.simplon.dreamteam.developers.services.LevelService;

import java.util.List;
@CrossOrigin("*")
@RequestMapping("/levels")
@RestController
public class LevelController {

    private LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public ResponseEntity<List<LevelView>>getLevels() {
        List<LevelView> levels = levelService.getAllLevels();
        return ResponseEntity.ok().body(levels);
    }
}
