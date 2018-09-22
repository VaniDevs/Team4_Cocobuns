package com.swiftavenue.vanhacks2018.controllers;

import java.util.Collection;
import java.util.List;
import com.swiftavenue.vanhacks2018.domain.Message;
import com.swiftavenue.vanhacks2018.repositories.PersonRepository;
import com.swiftavenue.vanhacks2018.repositories.SkillRepository;
import com.swiftavenue.vanhacks2018.repositories.dao.Person;
import com.swiftavenue.vanhacks2018.repositories.dao.Skill;
import com.swiftavenue.vanhacks2018.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private PersonRepository personRepo;

  @Autowired
  private SkillRepository skillRepo;

  @RequestMapping(value = "/messages", method = RequestMethod.GET)
  public ResponseEntity<String> getMessages() {
    List<Message> messages = messageService.getMessages();
    return new ResponseEntity(messages, HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/messages", method = RequestMethod.POST)
  public ResponseEntity<String> postMessage(@RequestBody Message message) {
    messageService.processMessage(message);
    return new ResponseEntity(HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/persons", method = RequestMethod.GET)
  public ResponseEntity<List<Person>> getPersons() {
    List<Person> results = personRepo.findAll();
    return new ResponseEntity<>(results, HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/persons", method = RequestMethod.POST)
  public ResponseEntity<String> getPersons(@RequestBody Person person) {
    personRepo.save(person);
    return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/persons/{lastName}", method = RequestMethod.GET)
  public ResponseEntity<Person> getPersonByLastName(@PathVariable String lastName) {
    Person result = personRepo.findByLastName(lastName);
    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/skills", method = RequestMethod.GET)
  public ResponseEntity<List<Skill>> getSkills() {
    List<Skill> result = skillRepo.findAll();
    return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
  }


  // TODO: File upload
}
