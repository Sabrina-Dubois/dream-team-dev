package co.simplon.dtbusiness.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.dtbusiness.entities.ProfileEntity;
import co.simplon.dtbusiness.repositories.ProfilesRepository;

@Service
public class ProfilesServiceImpl implements ProfilesService {
    private final ProfilesRepository repos;

    public ProfilesServiceImpl(ProfilesRepository repos) {
	this.repos = repos;
    }

    @Override
    public List<ProfileEntity> findAll() {
	return repos.findAll();
    }

    @Override
    public ProfileEntity findByInternalNumber(final String internalNumber) {
	return repos.findByInternalNumber(internalNumber);
//	final var profileEntity = repos.findByInternalNumber(internalNumber);
	// final var profile = new ProfileView(profileEntity., internalNumber,
	// internalNumber, internalNumber, internalNumber, internalNumber,
	// internalNumber);

    }
}
