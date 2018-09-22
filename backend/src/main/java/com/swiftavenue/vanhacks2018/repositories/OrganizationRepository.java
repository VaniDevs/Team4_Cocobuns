package com.swiftavenue.vanhacks2018.repositories;

import com.swiftavenue.vanhacks2018.repositories.dao.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
