package co.simplon.dtbusiness.services;

import java.util.List;

import co.simplon.dtbusiness.entities.Developer;

public interface DeveloperService {
    Developer findByInternalNumber(String internalNumber);

    List<Developer> findAll();

}
