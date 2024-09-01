package co.simplon.dtbusiness.services;

import co.simplon.dtbusiness.dtos.out.TopicView;
import co.simplon.dtbusiness.entities.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    List<TopicView> findTopicTechnicalsByName(String name);

    List<TopicView> findTopicBusinessAndSoftsByName(String name);

    Optional<Topic> findByName(String name);

    Topic saveTopic(Topic topic);
}
