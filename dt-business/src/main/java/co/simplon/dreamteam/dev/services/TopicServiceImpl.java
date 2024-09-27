package co.simplon.dreamteam.developers.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import co.simplon.dreamteam.developers.dtos.out.TopicView;
import co.simplon.dreamteam.developers.entities.Topic;
import co.simplon.dreamteam.developers.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {
    private TopicRepository repo;

    public TopicServiceImpl(TopicRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TopicView> findTopicTechnicalsByNameStartingWith(String name) {
        return repo.findProjectedByNameIgnoreCaseStartingWithAndIsTechnical(name, true);
    }

    @Override
    public List<TopicView> findTopicBusinessAndSoftsByNameStartingWith(String name) {
        return repo.findProjectedByNameIgnoreCaseStartingWithAndIsTechnical(name, false);
    }

    @Override
    public Optional<Topic> findByName(String name) {
        return repo.findByName(name);
    }

    @Transactional
    @Override
    public Topic saveTopic(Topic topic) {
        return repo.save(topic);
    }


}
