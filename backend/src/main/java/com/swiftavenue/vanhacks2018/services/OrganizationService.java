package com.swiftavenue.vanhacks2018.services;

import java.util.Optional;
import javax.transaction.Transactional;
import com.swiftavenue.vanhacks2018.repositories.OrganizationRepository;
import com.swiftavenue.vanhacks2018.repositories.PersonRepository;
import com.swiftavenue.vanhacks2018.repositories.dao.Organization;
import com.swiftavenue.vanhacks2018.repositories.dao.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PersonRepository personRepository;

    public void upsert(Organization organization) {
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
