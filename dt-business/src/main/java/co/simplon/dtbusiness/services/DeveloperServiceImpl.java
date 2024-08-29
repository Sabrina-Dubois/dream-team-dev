package co.simplon.dtbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.dtbusiness.entities.Developer;
import co.simplon.dtbusiness.repositories.DeveloperRepository;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository repos;

    public DeveloperServiceImpl(DeveloperRepository repos) {
	this.repos = repos;
    }

    @Override
    public List<Developer> findAll() {
	return repos.findAll();
    }

    @Override
    public Developer findByInternalNumber(final String internalNumber) {
	return repos.findByInternalNumber(internalNumber);
//	final var profileEntity = repos.findByInternalNumber(internalNumber);
	// final var profile = new ProfileView(profileEntity., internalNumber,
	// internalNumber, internalNumber, internalNumber, internalNumber,
	// internalNumber);

    }
}
