package co.simplon.dreamteam.developers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.developers.entities.Developer;
import co.simplon.dreamteam.developers.entities.Skill;
import co.simplon.dreamteam.developers.entities.Topic;

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
}
