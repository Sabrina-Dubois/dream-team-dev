package co.simplon.dreamteam.developers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.developers.dtos.out.DevProfileBriefView;
import co.simplon.dreamteam.developers.entities.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<DevProfileBriefView> findAllProjectedBy();

    List<DevProfileBriefView> findAllProjectedByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName,
	    String lastName);

    Developer findProjectedByInternalNumber(String internalNumber);
}
