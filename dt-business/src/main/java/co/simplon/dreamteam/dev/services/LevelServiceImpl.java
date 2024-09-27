package co.simplon.dreamteam.developers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.dreamteam.developers.dtos.out.LevelView;
import co.simplon.dreamteam.developers.repositories.LevelRepository;

@Service
public class LevelServiceImpl implements LevelService {

    public final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
	this.levelRepository = levelRepository;
    }

    @Override
    public List<LevelView> getAllLevels() {
	return levelRepository.findAllProjectedBy();
    }
}
