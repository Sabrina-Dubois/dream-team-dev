package co.simplon.dreamteam.dev.services;

import java.util.List;
import java.util.Optional;

import co.simplon.dreamteam.dev.dtos.out.TopicView;
import co.simplon.dreamteam.dev.entities.Topic;

public interface TopicService {
    List<TopicView> findTopicTechnicalsByNameStartingWith(String name);

    List<TopicView> findTopicBusinessAndSoftsByNameStartingWith(String name);

    Optional<Topic> findByName(String name);

    Topic saveTopic(Topic topic);
}
