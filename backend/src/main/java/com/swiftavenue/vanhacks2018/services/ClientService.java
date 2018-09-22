package com.swiftavenue.vanhacks2018.services;

import com.swiftavenue.vanhacks2018.repositories.ClientRepository;
import com.swiftavenue.vanhacks2018.repositories.dao.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Client getClientById(long clientId) {
        return clientRepository.getOne(clientId);
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

}
