package com.swiftavenue.vanhacks2018.controllers;

import java.io.IOException;
import java.util.List;
import com.swiftavenue.vanhacks2018.domain.XMNotificationProperties;
import com.swiftavenue.vanhacks2018.repositories.dao.Case;
import com.swiftavenue.vanhacks2018.services.CaseService;
import com.swiftavenue.vanhacks2018.services.XMNotificationService;
import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private XMNotificationService notificationService;


    @Value("${xmatters.notification.targetname:dwi2004@gmail.com}")
    private String notificationTargetName;

    @Value("${matters.notification.enabled:false}")
    private Boolean notificationEnabled;

    @RequestMapping(value = "/case", method = RequestMethod.POST)
    public ResponseEntity addCase(@RequestBody Case caze) {
        caseService.addCase(caze);

        try {
            // Send notification to xmatters
            XMNotificationProperties properties = XMNotificationProperties.newBuilder()
                .setClientContactPhone(caze.getClient().getPhoneNumber())
                .setClientEmail(caze.getClient().getEmail())
                .setDemographics(caze.getClient().getSociographics().toString())
                .setClientName(caze.getClient().getFirstName() + " " + caze.getClient().getLastName())
                .build();
            if (notificationEnabled) {
                notificationService.sendNotification(properties, notificationTargetName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/case", method = RequestMethod.GET)
    public ResponseEntity<Case> getCase(@RequestParam("id") long id) {
        Case caze = caseService.getCase(id);
        return new ResponseEntity<>(caze, HttpStatus.OK);
    }

    @RequestMapping(value = "/cases", method = RequestMethod.GET)
    public ResponseEntity<List<Case>> getCases() {
        List<Case> cases = caseService.getCases();
        return new ResponseEntity<>(cases, HttpStatus.OK);
    }

}
