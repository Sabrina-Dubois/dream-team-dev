package co.simplon.dtbusiness.services;

import java.util.List;

import co.simplon.dtbusiness.dtos.out.LevelView;
import co.simplon.dtbusiness.repositories.LevelRepository;
import org.springframework.stereotype.Service;

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
