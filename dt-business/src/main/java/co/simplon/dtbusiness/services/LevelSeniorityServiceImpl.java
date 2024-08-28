package co.simplon.dtbusiness.services;

import co.simplon.dtbusiness.dtos.out.LevelSeniorityView;
import co.simplon.dtbusiness.repositories.LevelSeniorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
