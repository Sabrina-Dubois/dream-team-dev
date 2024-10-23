package co.simplon.dreamteam.dev.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.dreamteam.dev.dtos.out.TopicView;
import co.simplon.dreamteam.dev.services.TopicService;

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
