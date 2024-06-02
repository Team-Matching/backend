package com.cbnu.teammatching.application.repository;

import com.cbnu.teammatching.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByApplicantIdAndPostId(Long memberId, Long postId);

    Optional<Application> findByApplicantEmailAndPostId(String memberEmail, Long postId);
}
