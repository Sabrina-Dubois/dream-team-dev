package co.simplon.dtbusiness.controllers;

import co.simplon.dtbusiness.dtos.out.LevelView;
import co.simplon.dtbusiness.dtos.out.TopicView;
import co.simplon.dtbusiness.services.LevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
