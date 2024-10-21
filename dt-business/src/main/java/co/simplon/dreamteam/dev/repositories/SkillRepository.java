package co.simplon.dreamteam.dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import co.simplon.dreamteam.dev.entities.Developer;
import co.simplon.dreamteam.dev.entities.Skill;
import co.simplon.dreamteam.dev.entities.Topic;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("SELECT sp FROM Skill sp " +
            "JOIN sp.developer d " +
            "JOIN sp.topic t " +
            "WHERE d.internalNumber = :internalNumber")
    List<Skill> findAllByDeveloperInternalNumber(String internalNumber);

    Optional<Skill> findByDeveloperAndTopic(Developer entity, Topic addedTopic);

    @Modifying
    @Transactional
    @Query("DELETE FROM Skill s WHERE s.developer = :developer AND s.topic.isTechnical = :isTechnical")
    void deleteAllByDeveloperAndTechnical(Developer developer, boolean isTechnical);
}
