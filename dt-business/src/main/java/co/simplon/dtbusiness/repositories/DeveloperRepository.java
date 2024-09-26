package co.simplon.dtbusiness.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dtbusiness.dtos.out.DevProfileBriefView;
import co.simplon.dtbusiness.entities.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<DevProfileBriefView> findAllProjectedBy();

    List<DevProfileBriefView> findAllProjectedByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName,
	    String lastName);

    Developer findProjectedByInternalNumber(String internalNumber);
}
