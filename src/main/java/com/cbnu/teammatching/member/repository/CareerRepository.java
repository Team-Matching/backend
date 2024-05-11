package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
}
