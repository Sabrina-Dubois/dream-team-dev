package co.simplon.dreamteam.dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.dev.entities.Filter;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {

	boolean existsByKeywordAndFirstTopicAndSecondTopicAndIdNot(String keyword, String firstTopic, String secondTopic,
			Long id);

	boolean existsByNameAndFirstTopicAndSecondTopicAndKeyword(String name, String firstTopic, String secondTopic,
			String keyword);

	// long countDeveloperId(long developerId);
}
