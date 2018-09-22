package com.swiftavenue.vanhacks2018.repositories;

import java.util.List;
import com.swiftavenue.vanhacks2018.repositories.dao.CaseNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseNoteRepository extends JpaRepository<CaseNote, Long> {
    List<CaseNote> findCaseNotesByCazeId(long caseId);
}
