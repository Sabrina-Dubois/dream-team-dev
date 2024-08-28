package co.simplon.dtbusiness.repositories;

import co.simplon.dtbusiness.controllers.LevelSeniority;
import co.simplon.dtbusiness.dtos.out.LevelSeniorityView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelSeniorityRepository extends JpaRepository<LevelSeniority, Long> {

    List<LevelSeniorityView> findAllProjectedBy();
}
