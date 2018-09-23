package com.swiftavenue.vanhacks2018.controllers;

import java.io.IOException;
import java.util.List;
import com.swiftavenue.vanhacks2018.devices.SmsSender;
import com.swiftavenue.vanhacks2018.domain.XMNotificationProperties;
import com.swiftavenue.vanhacks2018.repositories.dao.Case;
import com.swiftavenue.vanhacks2018.services.CaseService;
import com.swiftavenue.vanhacks2018.services.XMNotificationService;
import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaseController {
    @Autowired
    private CaseService caseService;

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private XMNotificationService notificationService;


    @Value("${xmatters.notification.targetname:dwi2004@gmail.com}")
    private String notificationTargetName;

    @Value("${xmatters.notification.enabled:false}")
    private Boolean notificationEnabled;

    @PostMapping(value = "/case")
    public Case addCase(@RequestBody Case caze) {
        Case updated = caseService.addCase(caze);

        try {
            // Send notification to xmatters
            XMNotificationProperties properties = XMNotificationProperties.newBuilder()
                .setClientContactPhone(caze.getClient().getPhoneNumber())
                .setClientEmail(caze.getClient().getEmail())
                .setDemographics(caze.getClient().getSociographics())
                .setClientName(caze.getClient().getFirstName() + " " + caze.getClient().getLastName())
                .build();
            if (notificationEnabled) {
                notificationService.sendNotification(properties, notificationTargetName);
            }
        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
        }
        return updated;
    }

    @RequestMapping(value = "/case/{id}", method = RequestMethod.GET)
    public ResponseEntity<Case> getCase(@PathVariable("id") long id) {
        Case caze = caseService.getCase(id);
        return new ResponseEntity<>(caze, HttpStatus.OK);
    }

    @PostMapping(value = "/case/{id}/approve")
    public Case approveCase(@PathVariable("id") long id) {
        caseService.approveCase(id);

        Case updated = caseService.getCase(id);
        return updated;
    }

    @PostMapping(value = "/case/{id}/schedule")
    public Case scheduleCase(
            @PathVariable("id") long id,
            @RequestParam("date") String appointmentTime,
            @RequestParam("phoneNo") String phoneNo,
            @RequestBody String message) {
        caseService.scheduleCase(id, appointmentTime);
        smsSender.sendSms(message, phoneNo);

        Case updated = caseService.getCase(id);
        return updated;
    }


    @RequestMapping(value = "/cases", method = RequestMethod.GET)
    public ResponseEntity<List<Case>> getCases() {
        List<Case> cases = caseService.getCases();

        cases.sort((o1, o2) -> {

            int o1Value = computerOrderStatus(o1.getCaseStatus());
            int o2Value = computerOrderStatus(o2.getCaseStatus());

            if (o1Value == o2Value) {
                return 0;
            } else if (o1Value < o2Value) {
                return -1;
            } else {
                return 1;
            }

        });

        return new ResponseEntity<>(cases, HttpStatus.OK);
    }

    protected static int computerOrderStatus(String status) {

        if (status == null) {
            return 100;
        }

        if (status.equals("NEW")) {
            return 20;
        }

        if (status.equals("APPROVED")) {
            return 10;
        }

        if (status.equals("SCHEDULED")) {
            return 5;
        }

        return -50;

    }

}
