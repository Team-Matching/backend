package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {

}
