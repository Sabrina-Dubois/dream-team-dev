package co.simplon.dtbusiness.repositories;

import co.simplon.dtbusiness.entities.Skill;
import co.simplon.dtbusiness.entities.SkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, SkillId> {
}
