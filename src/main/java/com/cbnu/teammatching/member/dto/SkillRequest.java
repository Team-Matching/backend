package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkillRequest {
    private List<SkillDto> skills;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkillDto {
        private String skill;
    }

    public static SkillDto of(Skill skill) {
        return new SkillDto(
                skill.getSkill()
        );
    }
}