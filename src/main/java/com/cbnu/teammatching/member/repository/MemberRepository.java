package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String email);
    boolean existsByUsername(String email);

    Optional<Member> findByUsername(String username);
}
