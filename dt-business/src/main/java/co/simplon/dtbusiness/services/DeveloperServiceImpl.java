package co.simplon.dtbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.dtbusiness.dtos.out.DeveloperView;
import co.simplon.dtbusiness.entities.Developer;
import co.simplon.dtbusiness.repositories.DeveloperRepository;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository repos;

    public DeveloperServiceImpl(DeveloperRepository repos) {
	this.repos = repos;
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
		e.getDescription(), e.getPicture(), e.getLinledin());
    };
}
