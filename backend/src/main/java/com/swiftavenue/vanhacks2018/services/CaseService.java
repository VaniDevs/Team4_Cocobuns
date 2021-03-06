package com.swiftavenue.vanhacks2018.services;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import com.swiftavenue.vanhacks2018.repositories.CaseRepository;
import com.swiftavenue.vanhacks2018.repositories.ClientRepository;
import com.swiftavenue.vanhacks2018.repositories.PersonRepository;
import com.swiftavenue.vanhacks2018.repositories.dao.Case;
import com.swiftavenue.vanhacks2018.repositories.dao.CaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CaseService {
    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Case addCase(Case caze) {
        if (!personRepository.exists(Example.of(caze.getOpenedBy()))) {
            personRepository.save(caze.getOpenedBy());
        }

        if (!clientRepository.exists(Example.of(caze.getClient()))) {
            clientRepository.save(caze.getClient());
        }

        return caseRepository.save(caze);
    }

    public void approveCase(long caseId) {
        Case caze = caseRepository.getOne(caseId);
        caze.setCaseStatus(CaseStatus.APPROVED.toString());

        caseRepository.save(caze);
    }

    public void scheduleCase(long caseId, String appointmentDate) {
        Case caze = caseRepository.getOne(caseId);
        caze.setCaseStatus(CaseStatus.SCHEDULED.toString());
        caze.setAppointmentDateTime(appointmentDate);

        caseRepository.save(caze);
    }


    public Case getCase(long id) {
        return caseRepository.getOne(id);
    }

    public List<Case> getCases() {
        return caseRepository.findAll();
    }


    public boolean exists(long caseId) {
        return caseRepository.existsById(caseId);
    }

}
