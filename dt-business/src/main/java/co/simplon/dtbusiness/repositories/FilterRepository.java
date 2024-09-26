package co.simplon.dtbusiness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dtbusiness.entities.Filter;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
	//FilterView findProjectedByNameAndKeywordAndFirstSkillAndSecondSkill();
	
}
