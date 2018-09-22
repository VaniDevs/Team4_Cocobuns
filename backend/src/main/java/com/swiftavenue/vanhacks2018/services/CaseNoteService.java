package com.swiftavenue.vanhacks2018.services;

import java.util.List;
import com.swiftavenue.vanhacks2018.repositories.CaseNoteRepository;
import com.swiftavenue.vanhacks2018.repositories.dao.CaseNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaseNoteService {
    @Autowired
    private CaseNoteRepository caseNoteRepository;

    public void addNote(CaseNote caseNote) {
        caseNoteRepository.save(caseNote);
    }

    public List<CaseNote> getNotes(long caseId) {
        return caseNoteRepository.findCaseNotesByCazeId(caseId);
    }
}
