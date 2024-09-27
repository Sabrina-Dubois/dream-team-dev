package co.simplon.dreamteam.dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.dev.dtos.out.TopicView;
import co.simplon.dreamteam.dev.entities.Topic;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<TopicView> findProjectedByNameIgnoreCaseStartingWithAndIsTechnical(String name, boolean isTechnical);

    Optional<Topic> findByName(String name);
}
