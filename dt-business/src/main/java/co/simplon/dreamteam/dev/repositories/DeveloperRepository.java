package co.simplon.dreamteam.dev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.dev.dtos.out.DeveloperItemView;
import co.simplon.dreamteam.dev.entities.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<DeveloperItemView> findAllProjectedBy();

    List<DeveloperItemView> findAllProjectedByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName,
	    String lastName);

    Developer findProjectedByInternalNumber(String internalNumber);
}
