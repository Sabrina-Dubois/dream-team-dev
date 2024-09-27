package co.simplon.dreamteam.dev.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.dreamteam.dev.dtos.out.LevelView;
import co.simplon.dreamteam.dev.repositories.LevelRepository;

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
