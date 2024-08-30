package co.simplon.dtbusiness.services;

import co.simplon.dtbusiness.dtos.out.TopicView;
import co.simplon.dtbusiness.entities.Topic;
import co.simplon.dtbusiness.repositories.TopicRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    private TopicRepository repo;

    public TopicServiceImpl(TopicRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TopicView> findTopicTechnicalsByName(String name) {
        return repo.findProjectedByNameAndIsTechnical(name, true);
    }

    @Override
    public List<TopicView> findTopicBusinessAndSoftsByName(String name) {
        return repo.findProjectedByNameAndIsTechnical(name, false);
    }

    @Override
    public Topic findByName(String name) {
        return repo.findByName(name);
    }

    @Transactional
    @Override
    public Topic saveTopic(Topic topic) {
        return repo.save(topic);
    }


}
