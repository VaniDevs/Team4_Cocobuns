package com.swiftavenue.vanhacks2018.services;

import javax.transaction.Transactional;
import com.swiftavenue.vanhacks2018.repositories.ClientRepository;
import com.swiftavenue.vanhacks2018.repositories.OrganizationRepository;
import com.swiftavenue.vanhacks2018.repositories.dao.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public Client getClientById(long clientId) {
        return clientRepository.getOne(clientId);
    }

    public Client upsert(Client client) {
        if (!organizationRepository.exists(Example.of(client.getReferredBy()))) {
            organizationRepository.save(client.getReferredBy());
        }

        return clientRepository.save(client);
    }

}
