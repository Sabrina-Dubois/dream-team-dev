package co.simplon.dtbusiness.services;

import co.simplon.dtbusiness.dtos.out.LevelSeniorityView;
import co.simplon.dtbusiness.repositories.LevelSeniorityRepository;

import java.util.List;

public class LevelSeniorityServiceImpl implements LevelSeniorityService {

    public LevelSeniorityRepository levelSeniorityRepository;

    public LevelSeniorityServiceImpl(LevelSeniorityRepository levelSeniorityRepository) {
        this.levelSeniorityRepository = levelSeniorityRepository;
    }

    @Override
    public List<LevelSeniorityView> getAllLevels() {

        return levelSeniorityRepository.findAllProjectedBy();
    }
}
