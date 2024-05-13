package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.Interest;
import com.cbnu.teammatching.member.domain.MemberInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberInterestRepository extends JpaRepository<MemberInterest, Long> {

    @Query("SELECT i FROM MemberInterest mi JOIN mi.interest i WHERE mi.member.id = :memberId")
    List<Interest> findInterestsByMemberId(@Param("memberId") Long memberId);

    boolean existsByMemberIdAndInterestId(Long memberId, Long interestId);
}
