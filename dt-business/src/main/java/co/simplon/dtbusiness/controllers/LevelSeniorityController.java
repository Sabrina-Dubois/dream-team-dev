package co.simplon.dtbusiness.controllers;

import co.simplon.dtbusiness.dtos.out.LevelSeniorityView;
import co.simplon.dtbusiness.services.LevelSeniorityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/levels")
@RestController
public class LevelSeniorityController {

    private LevelSeniorityService levelSeniorityService;

    public LevelSeniorityController(LevelSeniorityService levelSeniorityService) {
        this.levelSeniorityService = levelSeniorityService;
    }

    @GetMapping
    public List<LevelSeniorityView>getLevels() {
        List<LevelSeniorityView> levels = levelSeniorityService.getAllLevels();
        return levels;
    }
}
