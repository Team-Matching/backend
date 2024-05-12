package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.member.dto.SkillRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long id;

    @Column(unique = true)
    private String skill;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<MemberSkill> memberSkills = new ArrayList<>();

    public static Skill createSkill(SkillRequest.SkillDto skillDto, MemberSkill... memberSkills) {
        Skill skill = new Skill();
        skill.skill = skillDto.getSkill();
        for (MemberSkill memberSkill : memberSkills) {
            skill.addMemberSkill(memberSkill);
        }
        return skill;
    }

    public void addMemberSkill(MemberSkill memberSkill) {
        memberSkills.add(memberSkill);
        memberSkill.setSkill(this);
    }
}
