package co.simplon.dtbusiness.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.simplon.dtbusiness.dtos.out.DevProfileBriefView;
import co.simplon.dtbusiness.entities.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<DevProfileBriefView> findAllProjectedBy();

    Developer findProjectedByInternalNumber(String internalNumber);

    String SQL_QUERY = """
    	SELECT *
    	FROM t_developers dev
    	JOIN t_skills sk ON dev.id_dev = sk.dev_id
    	JOIN t_topics top ON top.id_topic = sk.topic_id
    	JOIN t_levels lev ON lev.id_level = sk.level_id
    	WHERE upper(first_name) LIKE upper(concat( '%', :firstName, '%'))
    	AND upper(last_name) LIKE upper(concat( '%', :lastName, '%'))
    	AND upper(top.skill_name) LIKE upper(concat( '%', :technicalTopic, '%'))
    	AND upper(lev.level_name) LIKE upper(concat( '%', :level, '%'));
    		    	""";

    @Query(value = SQL_QUERY, nativeQuery = true)
    List<Developer> findAllByFirstNameLastNameTehnicalSkillAndLevel(String firstName, String lastName,
	    String technicalTopic, String level);
}
