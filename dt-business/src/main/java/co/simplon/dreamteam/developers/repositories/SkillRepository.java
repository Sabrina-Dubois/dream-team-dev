package co.simplon.dreamteam.developers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.developers.entities.Developer;
import co.simplon.dreamteam.developers.entities.Skill;
import co.simplon.dreamteam.developers.entities.Topic;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findByDeveloperAndTopic(Developer entity, Topic addedTopic);

    @Modifying
    @Transactional
    @Query("DELETE FROM Skill s WHERE s.developer = :developer AND s.topic.isTechnical = :isTechnical")
    void deleteAllByDeveloperAndTechnical(Developer developer, boolean isTechnical);
}
