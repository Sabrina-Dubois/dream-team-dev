package co.simplon.dtbusiness.services;

import co.simplon.dtbusiness.dtos.out.TopicView;
import co.simplon.dtbusiness.entities.Topic;

import java.util.List;

public interface TopicService {
    List<TopicView> findTopicTechnicalsByName(String name);
    List<TopicView> findTopicBusinessAndSoftsByName(String name);

     Topic findByName(String name);
    Topic saveTopic(Topic topic);
}
