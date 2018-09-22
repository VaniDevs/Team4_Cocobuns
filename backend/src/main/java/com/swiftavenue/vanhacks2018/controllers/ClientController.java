package com.swiftavenue.vanhacks2018.controllers;

import com.swiftavenue.vanhacks2018.repositories.dao.Client;
import com.swiftavenue.vanhacks2018.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ResponseEntity getClient(@RequestParam("id") long clientId) {
        Client client = clientService.getClientById(clientId);
        return new ResponseEntity<>(client, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ResponseEntity addClient(@RequestBody Client client) {
        clientService.upsert(client);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
