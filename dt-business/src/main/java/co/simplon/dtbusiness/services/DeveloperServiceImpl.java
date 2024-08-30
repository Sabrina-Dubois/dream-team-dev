package co.simplon.dtbusiness.services;

import java.io.File;
import java.util.*;

import co.simplon.dtbusiness.dtos.in.DeveloperUpdate;
import co.simplon.dtbusiness.dtos.in.SkillCreateOrAdd;
import co.simplon.dtbusiness.dtos.out.SkillsView;
import co.simplon.dtbusiness.entities.Level;
import co.simplon.dtbusiness.entities.Skill;

import co.simplon.dtbusiness.entities.Topic;
import co.simplon.dtbusiness.repositories.LevelRepository;
import co.simplon.dtbusiness.repositories.SkillRepository;
import co.simplon.dtbusiness.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.simplon.dtbusiness.dtos.out.DeveloperView;
import co.simplon.dtbusiness.entities.Developer;
import co.simplon.dtbusiness.repositories.DeveloperRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final LevelRepository levelRepository;
    @Value("${dtbusiness.uploads.dest}")
    private String uploadsDest;
    private final DeveloperRepository repos;
    private final SkillRepository skillRepo;
    private final TopicRepository topicRepo;

    private final TopicService topicService;


    public DeveloperServiceImpl(DeveloperRepository repos, TopicRepository topicRepo, SkillRepository skillRepo, TopicRepository topicRepo1, LevelRepository levelRepository, TopicService topicService) {
        this.repos = repos;
        this.skillRepo = skillRepo;
        this.topicRepo = topicRepo1;
        this.levelRepository = levelRepository;
        this.topicService = topicService;
    }

    @Override
    public List<DeveloperView> findAll() {
        final List<Developer> devs = repos.findAll();
        return getDeveloperViews(devs);
    }

    @Override
    public DeveloperView findProjectedByInternalNumber(final String internalNumber) {
        final Developer dev = repos.findProjectedByInternalNumber(internalNumber);
        List<Skill> skillsOfDev = skillRepo.findAllByDeveloperInternalNumber(internalNumber);
        Collection<SkillsView> skillsView = getTopicsAndLevels(skillsOfDev);
        return getDeveloperView(dev, skillsView);
    }

    private Collection<SkillsView> getTopicsAndLevels(List<Skill> skills) {
        Collection<SkillsView> skillsView = new HashSet<>();

        for (Skill skill : skills) {
            skillsView.add(
                    new SkillsView(
                            skill.getTopic().getId(),
                            skill.getTopic().getName(),
                            skill.getTopic().isTechnical(),
                            skill.getTopic().isTechnical() ? skill.getLevel().getId() : null,
                            skill.getTopic().isTechnical() ? skill.getLevel().getName() : null)
            );
        }
        return skillsView;
    }

    private static List<DeveloperView> getDeveloperViews(final List<Developer> devs) {
        return devs.stream().map(dev -> getDeveloperView(dev, null)).toList();
    }

    private static DeveloperView getDeveloperView(Developer dev, Collection<SkillsView> skillsView) {
        return new DeveloperView(
                dev.getInternalNumber(),
                dev.getFirstName(),
                dev.getLastName(),
                dev.getEmail(),
                dev.getDescription(),
                dev.getPicture(),
                dev.getLinkedin(),
                skillsView);
    }

    public void create(DeveloperUpdate developer) {

        Developer entity = new Developer();

        entity.setDescription(developer.description());
        entity.setLinkedin(developer.linkedin());

        MultipartFile image = developer.picture();
        if (image != null) {
            String imageId = buildImageId(image);
            storeImage(image, imageId);
            entity.setPicture(imageId);
        }
        repos.save(entity);
    }

    private String buildImageId(MultipartFile picture) {
        UUID uuid = UUID.randomUUID();
        String name = picture.getOriginalFilename();
        int index = name.lastIndexOf('.');
        String ext = name.substring(index, name.length());
        return uuid + ext;
    }

    private void storeImage(MultipartFile image, String imageId) {
        try {
            String dest = String.format("%s/%s", uploadsDest, imageId);
            File file = new File(dest);
            image.transferTo(file);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateDeveloper(String internalNumber, DeveloperUpdate developer) {

        Developer entity = repos.findProjectedByInternalNumber(internalNumber);
        entity.setDescription(developer.description());
        entity.setLinkedin(developer.linkedin());
        MultipartFile image = developer.picture();

        Set<Skill> skillsAddDev = new HashSet<>();

        Collection<SkillCreateOrAdd> skillsdev = developer.skills();

        if (skillsdev != null) {
            for (SkillCreateOrAdd skill : skillsdev) {
                Optional<Topic> topic = topicRepo.findById(skill.id());
                if (skill.isTechnical()) {
                    if (topic.isEmpty()) {
                        Topic newTopic = new Topic();
                        newTopic.setName(skill.name());
                        newTopic.setTechnical(true);
                        Topic addedTopic = topicService.saveTopic(newTopic);
                        Skill newSkill = new Skill();
                        newSkill.setTopic(addedTopic);
                        Optional<Level> level = levelRepository.findById(skill.level());
                        level.ifPresent(newSkill::setLevel);
                        Skill skillDev = saveSkill(newSkill);
                        skillsAddDev.add(skillDev);

                    }
                } else {
                    if (topic.isEmpty()) {
                        Topic newTopic = new Topic();
                        newTopic.setName(skill.name());
                        newTopic.setTechnical(false);
                        Topic addedTopic = topicService.saveTopic(newTopic);
                        Skill newSkill = new Skill();
                        newSkill.setTopic(addedTopic);
                        Skill skillDev = saveSkill(newSkill);
                        skillsAddDev.add(skillDev);
                    }
                }
            }
        }

        entity.setSkills(skillsAddDev);
        if (image != null) {
            String imageId = buildImageId(image);
            storeImage(image, imageId);
            entity.setPicture(imageId);
        }
        repos.save(entity);
    }
    
    @Override
    public Skill saveSkill(Skill skill) {
        return skillRepo.save(skill);
    }
}