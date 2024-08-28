package co.simplon.dtbusiness.repositories;


import co.simplon.dtbusiness.dtos.out.LevelSeniorityView;
import co.simplon.dtbusiness.entities.LevelSeniority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelSeniorityRepository extends JpaRepository<LevelSeniority, Long> {

    List<LevelSeniorityView> findAllProjectedBy();
}
