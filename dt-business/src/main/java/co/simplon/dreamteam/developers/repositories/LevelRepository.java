package co.simplon.dreamteam.developers.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.developers.dtos.out.LevelView;
import co.simplon.dreamteam.developers.entities.Level;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    List<LevelView> findAllProjectedBy();

    Optional<Level> findOneOptionalByName(String name);
}
