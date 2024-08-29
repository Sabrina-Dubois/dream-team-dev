package co.simplon.dtbusiness.controllers;

import co.simplon.dtbusiness.dtos.out.TopicView;
import co.simplon.dtbusiness.services.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
