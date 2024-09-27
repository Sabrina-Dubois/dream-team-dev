package co.simplon.dreamteam.developers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.developers.entities.Filter;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
	//FilterView findProjectedByNameAndKeywordAndFirstSkillAndSecondSkill();
	
}
