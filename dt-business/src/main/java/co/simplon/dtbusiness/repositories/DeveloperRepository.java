package co.simplon.dtbusiness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dtbusiness.entities.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Developer findByInternalNumber(String internalNumber);
}
