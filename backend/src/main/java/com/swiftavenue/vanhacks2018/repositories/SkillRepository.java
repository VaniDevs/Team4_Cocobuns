package com.swiftavenue.vanhacks2018.repositories;

import com.swiftavenue.vanhacks2018.repositories.dao.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
