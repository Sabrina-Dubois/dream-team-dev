package co.simplon.dreamteam.dev.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.dreamteam.dev.dtos.out.LevelView;
import co.simplon.dreamteam.dev.entities.Level;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    List<LevelView> findAllProjectedBy();

    Optional<Level> findOneOptionalByName(String name);
}
