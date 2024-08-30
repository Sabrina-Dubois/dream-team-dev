package co.simplon.dtbusiness.repositories;

import co.simplon.dtbusiness.entities.Skill;
import co.simplon.dtbusiness.entities.SkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SkillRepository extends JpaRepository<Skill, SkillId> {
    @Query("SELECT sp FROM Skill sp " +
            "JOIN sp.developer d " +
            "JOIN sp.topic t " +
            "WHERE d.internalNumber = :internalNumber")
    List<Skill> findAllByDeveloperInternalNumber(String internalNumber);
}
