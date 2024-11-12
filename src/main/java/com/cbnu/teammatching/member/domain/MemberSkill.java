package com.cbnu.teammatching.member.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public static MemberSkill createMemberSkill(Member member) {
        MemberSkill memberSkill = new MemberSkill();
        memberSkill.member = member;
        return memberSkill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
