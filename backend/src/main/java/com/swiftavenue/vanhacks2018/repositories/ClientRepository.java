package com.swiftavenue.vanhacks2018.repositories;

import com.swiftavenue.vanhacks2018.repositories.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
