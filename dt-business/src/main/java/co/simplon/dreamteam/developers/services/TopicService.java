package co.simplon.dreamteam.developers.services;

import java.util.List;
import java.util.Optional;

import co.simplon.dreamteam.developers.dtos.out.TopicView;
import co.simplon.dreamteam.developers.entities.Topic;

public interface TopicService {
    List<TopicView> findTopicTechnicalsByNameStartingWith(String name);

    List<TopicView> findTopicBusinessAndSoftsByNameStartingWith(String name);

    Optional<Topic> findByName(String name);

    Topic saveTopic(Topic topic);
}
