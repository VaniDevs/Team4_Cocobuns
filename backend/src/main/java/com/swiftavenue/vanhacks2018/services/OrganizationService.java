package com.swiftavenue.vanhacks2018.services;

import com.swiftavenue.vanhacks2018.repositories.OrganizationRepository;
import com.swiftavenue.vanhacks2018.repositories.dao.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public void addOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    public Organization getOrganization(long id) {
        return organizationRepository.getOne(id);
    }
}
