package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.member.dto.SkillRequest;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String skill;

    public static Skill createSkill(Member member, SkillRequest.SkillDto skillDto) {
        Skill skill = new Skill();
        skill.member = member;
        skill.skill = skillDto.getSkill();
        member.getSkills().add(skill);
        return skill;
    }
}
