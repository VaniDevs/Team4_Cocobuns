package com.swiftavenue.vanhacks2018.controllers;

import java.util.Optional;
import com.swiftavenue.vanhacks2018.repositories.dao.Person;
import com.swiftavenue.vanhacks2018.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity postPerson(@RequestBody Person person) {
        organizationService.addPerson(person);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/people", method = RequestMethod.PUT)
    public ResponseEntity updatePerson(@RequestBody Person person) {
        organizationService.updatePerson(person);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        Optional<Person> r = organizationService.findPerson(id);
        if (r.isPresent()) {
            return new ResponseEntity(r.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
