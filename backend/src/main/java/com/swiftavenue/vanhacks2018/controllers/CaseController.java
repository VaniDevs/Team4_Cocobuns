package com.swiftavenue.vanhacks2018.controllers;

import com.swiftavenue.vanhacks2018.repositories.dao.Case;
import com.swiftavenue.vanhacks2018.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaseController {
    @Autowired
    private CaseService caseService;

    @RequestMapping(value = "/case", method = RequestMethod.POST)
    public ResponseEntity addCase(@RequestBody Case caze) {
        caseService.upsert(caze);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/case", method = RequestMethod.GET)
    public ResponseEntity<Case> getCase(@RequestParam("id") long id) {
        Case caze = caseService.getCase(id);
        return new ResponseEntity<>(caze, HttpStatus.OK);
    }
}
