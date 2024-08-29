package co.simplon.dtbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.dtbusiness.dtos.out.LevelSeniorityView;
import co.simplon.dtbusiness.repositories.LevelSeniorityRepository;

@Service
public class LevelSeniorityServiceImpl implements LevelSeniorityService {

    public final LevelSeniorityRepository levelSeniorityRepository;

    public LevelSeniorityServiceImpl(LevelSeniorityRepository levelSeniorityRepository) {
	this.levelSeniorityRepository = levelSeniorityRepository;
    }

    @Override
    public List<LevelSeniorityView> getAllLevels() {
	return levelSeniorityRepository.findAllProjectedBy();
    }
}
