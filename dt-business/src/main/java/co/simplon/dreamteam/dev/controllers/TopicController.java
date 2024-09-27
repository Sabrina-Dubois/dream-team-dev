package co.simplon.dreamteam.developers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.simplon.dreamteam.developers.dtos.out.TopicView;
import co.simplon.dreamteam.developers.services.TopicService;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/topics")
@RestController
public class TopicController {

    private TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<List<TopicView>> findTopicTechnical(
            @RequestParam(value = "label", defaultValue = "", required = true) String name,
            @RequestParam(value = "isTechnical", defaultValue = "true", required = true) boolean isTechnical) {

        List<TopicView> topics;
        if (isTechnical) {
            topics = service.findTopicTechnicalsByNameStartingWith(name);
        } else {
            topics = service.findTopicBusinessAndSoftsByNameStartingWith(name);
        }
        return ResponseEntity.ok().body(topics);
    }
}
