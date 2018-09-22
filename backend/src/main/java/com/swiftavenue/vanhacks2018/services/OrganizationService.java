package com.swiftavenue.vanhacks2018.services;

import java.util.Optional;
import com.swiftavenue.vanhacks2018.repositories.OrganizationRepository;
import com.swiftavenue.vanhacks2018.repositories.PersonRepository;
import com.swiftavenue.vanhacks2018.repositories.dao.Organization;
import com.swiftavenue.vanhacks2018.repositories.dao.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PersonRepository personRepository;

    public void addOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    public Organization getOrganization(long id) {
        return organizationRepository.getOne(id);
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public Optional<Person> findPerson(long personId) {
        return personRepository.findById(personId);
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }
}
