package com.swiftavenue.vanhacks2018.controllers;

import com.swiftavenue.vanhacks2018.repositories.dao.Organization;
import com.swiftavenue.vanhacks2018.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationContoller {
    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    public ResponseEntity postOrganization(@RequestBody Organization organization) {
        organizationService.upsert(organization);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Organization> getOrganization(@RequestParam("id") long organizationId) {
        Organization organization = organizationService.getOrganization(organizationId);
        return new ResponseEntity<>(organization, HttpStatus.ACCEPTED);
    }
}
