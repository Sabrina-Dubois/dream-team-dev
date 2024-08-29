package co.simplon.dtbusiness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.dtbusiness.entities.ProfileEntity;

@Repository
public interface ProfilesRepository extends JpaRepository<ProfileEntity, Long> {
    ProfileEntity findByInternalNumber(@Param(value = "internalNumber") String internalNumber);
}