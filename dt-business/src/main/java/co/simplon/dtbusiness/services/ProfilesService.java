package co.simplon.dtbusiness.services;

import java.util.List;

import co.simplon.dtbusiness.entities.ProfileEntity;

public interface ProfilesService {
    ProfileEntity findByInternalNumber(String internalNumber);

    List<ProfileEntity> findAll();

}
