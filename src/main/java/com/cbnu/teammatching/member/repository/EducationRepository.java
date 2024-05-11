package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

}
