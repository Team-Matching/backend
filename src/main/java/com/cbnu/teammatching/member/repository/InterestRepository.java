package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    boolean existsByInterest(String interest);

    Optional<Interest> findByInterest(String interest);
}
