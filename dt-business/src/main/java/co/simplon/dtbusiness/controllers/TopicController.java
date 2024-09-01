package co.simplon.dtbusiness.controllers;

import co.simplon.dtbusiness.dtos.out.TopicView;
import co.simplon.dtbusiness.services.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "label", required = true) String name,
            @RequestParam(value = "isTechnical", defaultValue = "true" ,required = true) boolean isTechnical){

        List<TopicView> topics;
        if(isTechnical){
            topics = service.findTopicTechnicalsByName(name);
        }else{
            topics= service.findTopicBusinessAndSoftsByName(name);
        }
        return ResponseEntity.ok().body(topics);
    }
}
