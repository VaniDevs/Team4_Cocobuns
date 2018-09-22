package com.swiftavenue.vanhacks2018.repositories;

import com.swiftavenue.vanhacks2018.repositories.dao.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
}
