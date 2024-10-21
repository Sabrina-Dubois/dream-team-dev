package co.simplon.dreamteam.dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD:dt-business/src/main/java/co/simplon/dreamteam/developers/repositories/SkillRepository.java
import co.simplon.dreamteam.developers.entities.Developer;
import co.simplon.dreamteam.developers.entities.Skill;
import co.simplon.dreamteam.developers.entities.Topic;
import org.springframework.transaction.annotation.Transactional;
=======
import co.simplon.dreamteam.dev.entities.Developer;
import co.simplon.dreamteam.dev.entities.Skill;
import co.simplon.dreamteam.dev.entities.Topic;
>>>>>>> 0115b2c63c965443f35321160de409cd400ec344:dt-business/src/main/java/co/simplon/dreamteam/dev/repositories/SkillRepository.java

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
