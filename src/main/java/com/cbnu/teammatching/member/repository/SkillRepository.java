package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    boolean existsBySkill(String skill);

    Optional<Skill> findBySkill(String skill);
}
