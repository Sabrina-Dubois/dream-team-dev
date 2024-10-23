package co.simplon.dreamteam.dev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.simplon.dreamteam.dev.dtos.out.TopicView;
import co.simplon.dreamteam.dev.entities.Topic;
import co.simplon.dreamteam.dev.repositories.TopicRepository;
import jakarta.transaction.Transactional;

@Service
public class TopicService {
	private TopicRepository repo;

	public TopicService(TopicRepository repo) {
		this.repo = repo;
	}

	public List<TopicView> findTopicTechnicalsByNameStartingWith(String name) {
		return repo.findProjectedByNameIgnoreCaseStartingWithAndIsTechnical(name, true);
	}

	public List<TopicView> findTopicBusinessAndSoftsByNameStartingWith(String name) {
		return repo.findProjectedByNameIgnoreCaseStartingWithAndIsTechnical(name, false);
	}

	public Optional<Topic> findByName(String name) {
		return repo.findByName(name);
	}

	@Transactional
	public Topic saveTopic(Topic topic) {
		return repo.save(topic);
	}

}
