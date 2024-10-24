package co.simplon.dreamteam.dev.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.dreamteam.dev.dtos.in.CreateSkill;
import co.simplon.dreamteam.dev.dtos.in.UpdateDeveloper;
import co.simplon.dreamteam.dev.dtos.out.DeveloperItemView;
import co.simplon.dreamteam.dev.dtos.out.DeveloperView;
import co.simplon.dreamteam.dev.dtos.out.SkillsView;
import co.simplon.dreamteam.dev.entities.Developer;
import co.simplon.dreamteam.dev.entities.Skill;
import co.simplon.dreamteam.dev.entities.Topic;
import co.simplon.dreamteam.dev.repositories.DeveloperRepository;
import co.simplon.dreamteam.dev.repositories.LevelRepository;
import co.simplon.dreamteam.dev.repositories.SkillRepository;

@Transactional
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final LevelRepository levelRepository;
    @Value("${dtbusiness.uploads.dest}")
    private String uploadsDest;
    private final DeveloperRepository repos;
    private final SkillRepository skillRepo;
    private final TopicService topicService;

    public DeveloperServiceImpl(DeveloperRepository repos, SkillRepository skillRepo, LevelRepository levelRepository,
                                TopicService topicService) {
        this.repos = repos;
        this.skillRepo = skillRepo;
        this.levelRepository = levelRepository;
        this.topicService = topicService;
    }

    @Override
    public List<DeveloperItemView> findAllDeveloperItems() {
        return repos.findAllProjectedBy();
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
            skillsView.add(new SkillsView(skill.getTopic().getId(), skill.getTopic().getName(),
                    skill.getTopic().isTechnical(), skill.getTopic().isTechnical() ? skill.getLevel().getId() : null,
                    skill.getTopic().isTechnical() ? skill.getLevel().getName() : null));
        }
        return skillsView;
    }

    private static DeveloperView getDeveloperView(Developer dev, Collection<SkillsView> skillsView) {
        return new DeveloperView(dev.getInternalNumber(), dev.getFirstName(), dev.getLastName(), dev.getEmail(),
                dev.getDescription(), dev.getPicture(), dev.getLinkedin(), skillsView);
    }

    public void create(UpdateDeveloper developer) {

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
    public void updateDeveloper(String internalNumber, UpdateDeveloper developer, List<CreateSkill> skillsJson) {
        Developer entity = repos.findProjectedByInternalNumber(internalNumber);
        updateDeveloperDetails(entity, developer);
        handleImage(developer.picture(), entity);

        boolean hasTechnicalSkills = skillsJson.stream().anyMatch(CreateSkill::isTechnical);
        boolean hasSoftSkills = skillsJson.stream().anyMatch(skill -> !skill.isTechnical());

        // Remove existing skills based on the presence of technical or soft skills
        if (hasTechnicalSkills) {
            entity.getSkills().removeIf(skill -> skill.getTopic().isTechnical());
            skillRepo.deleteAllByDeveloperAndTechnical(entity, true);
        }
        if (hasSoftSkills) {
            entity.getSkills().removeIf(skill -> !skill.getTopic().isTechnical());
            skillRepo.deleteAllByDeveloperAndTechnical(entity, false);
        }

        // Add new skills
        Set<Skill> newSkills = new HashSet<>();
        if (skillsJson != null) {
            for (CreateSkill skill : skillsJson) {
                Topic topic = topicService.findByName(skill.name()).orElseGet(() -> createNewTopic(skill));
                if (skill.isTechnical()) {
                    topic.setTechnical(true);
                }
                Skill newSkill = createNewSkill(entity, topic, skill);
                newSkills.add(saveSkill(newSkill));
            }
        }

        entity.setSkills(newSkills);
        repos.save(entity);
    }

    private void updateDeveloperDetails(Developer entity, UpdateDeveloper developer) {
        entity.setDescription(developer.description());
        entity.setLinkedin(developer.linkedin());
    }

    private void handleImage(MultipartFile image, Developer entity) {
        if (image != null) {
            String imageId = buildImageId(image);
            storeImage(image, imageId);
            entity.setPicture(imageId);
        }
    }

    private Skill createNewSkill(Developer developer, Topic topic, CreateSkill skill) {
        Skill newSkill = new Skill();
        newSkill.setTopic(topic);
        newSkill.setDeveloper(developer);
        if (skill.isTechnical()) {
            levelRepository.findOneOptionalByName(skill.levelName()).ifPresent(newSkill::setLevel);
        }
        return newSkill;
    }

    @Override
    public Skill saveSkill(Skill skill) {
        Skill savedSkill = skillRepo.save(skill);
        return savedSkill;
    }

    private Topic createNewTopic(CreateSkill skill) {
        Topic newTopic = new Topic();
        newTopic.setName(skill.name());
        newTopic.setTechnical(skill.isTechnical());
        return topicService.saveTopic(newTopic);
    }

    @Override
    public List<DeveloperItemView> findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(String firstName,
                                                                                              String lastName, String technicalTopic, String level) {
        List<Developer> devs = repos.findAllByFirstNameLastNameTehnicalSkillAndLevel(firstName, lastName,
                technicalTopic, level);
        return getDevProfileBriefViews(devs);
    }

    private List<DeveloperItemView> getDevProfileBriefViews(List<Developer> devs) {
        List<DeveloperItemView> devsList = new ArrayList<DeveloperItemView>();
        devs.forEach(dev -> devsList.add(getDevProfileBriefView(dev)));
        return devsList;
    }

    private static DeveloperItemView getDevProfileBriefView(Developer dev) {
        return new DeveloperItemView(dev.getFirstName(), dev.getLastName(), dev.getEmail(), dev.getPicture());
    }
}