package co.simplon.dtbusiness.services;

import java.util.List;

import co.simplon.dtbusiness.dtos.out.DeveloperView;

public interface DeveloperService {
    DeveloperView findProjectedByInternalNumber(String internalNumber);

    List<DeveloperView> findAll();

}
