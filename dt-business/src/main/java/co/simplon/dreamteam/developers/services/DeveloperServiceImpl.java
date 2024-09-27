package co.simplon.dreamteam.developers.services;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.dreamteam.developers.dtos.in.UpdateDeveloper;
import co.simplon.dreamteam.developers.dtos.in.CreateOrAddSkill;
import co.simplon.dreamteam.developers.dtos.out.DeveloperBriefView;
import co.simplon.dreamteam.developers.dtos.out.DeveloperView;
import co.simplon.dreamteam.developers.dtos.out.SkillsView;
import co.simplon.dreamteam.developers.entities.Developer;
import co.simplon.dreamteam.developers.entities.Level;
import co.simplon.dreamteam.developers.entities.Skill;
import co.simplon.dreamteam.developers.entities.Topic;
import co.simplon.dreamteam.developers.repositories.DeveloperRepository;
import co.simplon.dreamteam.developers.repositories.LevelRepository;
import co.simplon.dreamteam.developers.repositories.SkillRepository;

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
    public List<DeveloperBriefView> findAllDeveloperBriefs() {
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
    public void updateDeveloper(String internalNumber, UpdateDeveloper developer, List<CreateOrAddSkill> skillsJson) {

	Developer entity = repos.findProjectedByInternalNumber(internalNumber);
	entity.setDescription(developer.description());
	entity.setLinkedin(developer.linkedin());
	MultipartFile image = developer.picture();

	Set<Skill> skillsAddDev = new HashSet<>();

	Collection<CreateOrAddSkill> skillsdev = skillsJson;

	if (skillsdev != null) {
	    for (CreateOrAddSkill skill : skillsdev) {
		Optional<Topic> topic = topicService.findByName(skill.name());
		if (topic.isEmpty()) {
		    if (skill.isTechnical()) {
			Topic newTopic = new Topic();
			newTopic.setName(skill.name());
			newTopic.setTechnical(true);
			Topic addedTopic = topicService.saveTopic(newTopic);
			Skill newSkill = new Skill();
			newSkill.setTopic(addedTopic);
			newSkill.setDeveloper(entity);
			Optional<Level> level = levelRepository.findOneOptionalByName(skill.levelName());
			level.ifPresent(newSkill::setLevel);
			newSkill = saveSkill(newSkill);
			skillsAddDev.add(newSkill);

		    } else {
			Topic newTopic = new Topic();
			newTopic.setName(skill.name());
			newTopic.setTechnical(false);
			Topic addedTopic = topicService.saveTopic(newTopic);
			Skill newSkill = new Skill();
			newSkill.setTopic(addedTopic);
			newSkill.setDeveloper(entity);
			newSkill = saveSkill(newSkill);
			skillsAddDev.add(newSkill);

		    }
		} else {
		    if (skill.isTechnical()) {
			Topic addedTopic = topic.get();
			Skill newSkill = new Skill();
			newSkill.setTopic(addedTopic);
			newSkill.setDeveloper(entity);

			if (skillRepo.findByDeveloperAndTopic(entity, addedTopic).isEmpty()) {
			    Optional<Level> level = levelRepository.findOneOptionalByName(skill.levelName());
			    level.ifPresent(newSkill::setLevel);
			    newSkill = saveSkill(newSkill);
			} else {
			    newSkill = skillRepo.findByDeveloperAndTopic(entity, addedTopic).get();
			    Optional<Level> level = levelRepository.findOneOptionalByName(skill.levelName());
			    level.ifPresent(newSkill::setLevel);
			}
			skillsAddDev.add(newSkill);

		    } else {

			Topic addedTopic = topic.get();
			Skill newSkill = new Skill();
			newSkill.setTopic(addedTopic);
			newSkill.setDeveloper(entity);
			if (skillRepo.findByDeveloperAndTopic(entity, addedTopic).isEmpty()) {
			    Skill skillDev = saveSkill(newSkill);
			    skillsAddDev.add(skillDev);
			}
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
	Skill savedSkill = skillRepo.save(skill);
	return savedSkill;
    }

    @Override
    public List<DeveloperBriefView> findAllDevelopersByLastNameFirstNameTechnicalSkillAndLevel(String firstName,
	    String lastName) {
	return repos.findAllProjectedByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
    }
}
