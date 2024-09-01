package co.simplon.dtbusiness.repositories;


import co.simplon.dtbusiness.dtos.out.LevelView;
import co.simplon.dtbusiness.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    List<LevelView> findAllProjectedBy();

    Optional<Level> findOneOptionalByName(String name);
}
