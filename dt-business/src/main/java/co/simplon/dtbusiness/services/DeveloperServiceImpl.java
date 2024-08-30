package co.simplon.dtbusiness.services;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import co.simplon.dtbusiness.dtos.in.DeveloperUpdate;
import co.simplon.dtbusiness.dtos.in.SkillCreateOrAdd;
import co.simplon.dtbusiness.entities.Topic;
import co.simplon.dtbusiness.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.simplon.dtbusiness.dtos.out.DeveloperView;
import co.simplon.dtbusiness.entities.Developer;
import co.simplon.dtbusiness.repositories.DeveloperRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final TopicRepository topicRepository;
    @Value("${dtbusiness.uploads.dest}")
    private String uploadsDest;
    private final DeveloperRepository repos;

    public DeveloperServiceImpl(DeveloperRepository repos, TopicRepository topicRepository) {
	this.repos = repos;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<DeveloperView> findAll() {
	final List<Developer> devs = repos.findAll();
	return getDeveloperViews(devs);
    }

    @Override
    public DeveloperView findProjectedByInternalNumber(final String internalNumber) {
	final Developer dev = repos.findProjectedByInternalNumber(internalNumber);
	return getDeveloperView(dev);
    }

    private static List<DeveloperView> getDeveloperViews(final List<Developer> devs) {
	return devs.stream().map(e -> getDeveloperView(e)).toList();
    }

    private static DeveloperView getDeveloperView(Developer e) {
	return new DeveloperView(e.getInternalNumber(), e.getFirstName(), e.getLastName(), e.getEmail(),
		e.getDescription(), e.getPicture(), e.getLinkedin());
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
    public void updateDeveloper(Long id, DeveloperUpdate developer) {

        Developer entity = repos.findById(id).get();
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
}