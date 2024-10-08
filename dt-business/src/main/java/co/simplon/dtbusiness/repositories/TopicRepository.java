package co.simplon.dtbusiness.repositories;

import co.simplon.dtbusiness.dtos.out.TopicView;
import co.simplon.dtbusiness.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<TopicView> findProjectedByNameAndIsTechnical(String name, boolean isTechnical);

    Topic findByName(String name);
}
