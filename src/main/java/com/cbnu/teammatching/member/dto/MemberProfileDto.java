package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Interest;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.domain.Skill;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberProfileDto {

    private List<CareerDto> careers;

    private List<CertificationDto> certifications;

    private List<EducationDto> educations;

    private List<InterestRequest.InterestDto> interests;

    private List<SkillRequest.SkillDto> skills;

    public static MemberProfileDto of(Member member, List<Interest> interests, List<Skill> skills) {
        MemberProfileDto memberProfileDto = new MemberProfileDto();
        memberProfileDto.careers = member.getCareers().stream().map(CareerDto::of).collect(Collectors.toList());
        memberProfileDto.certifications = member.getCertifications().stream().map(CertificationDto::of).collect(Collectors.toList());
        memberProfileDto.educations = member.getEducations().stream().map(EducationDto::of).collect(Collectors.toList());
        memberProfileDto.interests = interests.stream().map(InterestRequest::of).collect(Collectors.toList());
        memberProfileDto.skills = skills.stream().map(SkillRequest::of).collect(Collectors.toList());
        return memberProfileDto;
    }
}
