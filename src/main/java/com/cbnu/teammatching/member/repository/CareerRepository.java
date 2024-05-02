package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.Career;
import com.cbnu.teammatching.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    Optional<Career> findByCompany(String company);

    List<Career> findAllByMember(Member member);
}
