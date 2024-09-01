package co.simplon.dtbusiness.repositories;

import co.simplon.dtbusiness.entities.Developer;
import co.simplon.dtbusiness.entities.Skill;
import co.simplon.dtbusiness.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
