package com.swiftavenue.vanhacks2018.controllers;

import java.util.List;
import com.swiftavenue.vanhacks2018.repositories.dao.Case;
import com.swiftavenue.vanhacks2018.repositories.dao.CaseNote;
import com.swiftavenue.vanhacks2018.services.CaseNoteService;
import com.swiftavenue.vanhacks2018.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaseNoteController {
    @Autowired
    private CaseService caseService;

    @Autowired
    private CaseNoteService caseNoteService;

    @RequestMapping(value = "/case/{caseId}/note", method = RequestMethod.POST)
    public ResponseEntity addNote(@PathVariable long caseId, @RequestBody CaseNote caseNote) {
        if (!caseService.exists(caseId)) {
            return new ResponseEntity<>("Case not found", HttpStatus.NOT_FOUND);
        }

        Case caze = new Case();
        caze.setId(caseId);
        caseNote.setCaze(caze);

        caseNoteService.addNote(caseNote);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/case/{caseId}/notes", method = RequestMethod.GET)
    public ResponseEntity<List<CaseNote>> getCaseNotes(@PathVariable long caseId) {
        List<CaseNote> caseNotes = caseNoteService.getNotes(caseId);
        return new ResponseEntity<>(caseNotes, HttpStatus.OK);
    }
}
