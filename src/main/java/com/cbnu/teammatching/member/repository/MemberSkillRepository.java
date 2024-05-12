package com.cbnu.teammatching.member.repository;

import com.cbnu.teammatching.member.domain.MemberSkill;
import com.cbnu.teammatching.member.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberSkillRepository extends JpaRepository<MemberSkill, Long> {

    @Query("SELECT s FROM MemberSkill ms JOIN ms.skill s WHERE ms.member.id = :memberId")
    List<Skill> findSkillsByMemberId(@Param("memberId") Long memberId);

    boolean existsByMemberIdAndSkillId(Long memberId, Long skillId);
}
