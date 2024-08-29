package co.simplon.dtbusiness.services;

import co.simplon.dtbusiness.dtos.out.TopicView;

import java.util.List;

public interface TopicService {
    List<TopicView> findTopicTechnicalsByName(String name);
    List<TopicView> findTopicBusinessAndSoftsByName(String name);
}
