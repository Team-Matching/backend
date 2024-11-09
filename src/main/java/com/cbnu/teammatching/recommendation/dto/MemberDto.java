package com.cbnu.teammatching.recommendation.dto;


import com.cbnu.teammatching.member.domain.Career;
import com.cbnu.teammatching.member.domain.Certification;
import com.cbnu.teammatching.member.domain.Education;
import com.cbnu.teammatching.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String email;
    private String name;
    private String nickname;
    private String careers;
    private String certifications;
    private String educations;
    private String interests;
    private String skills;

    public static MemberDto of(Member member) {
        StringBuilder sb = new StringBuilder();
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(member.getEmail());
        memberDto.setName(member.getName());
        memberDto.setNickname(member.getNickname());
        for(Career career : member.getCareers()) {
            sb.append(career.getRole())
                    .append(" ")
                    .append(career.getCompany())
                    .append(" ")
                    .append(career.getDescription())
                    .append(" ");
        }
        memberDto.setCareers(sb.toString());
        for(Certification certification : member.getCertifications()) {
            sb.append(certification.getCertificationName()).append(" ");
        }
        memberDto.setCertifications(sb.toString());
        for(Education education : member.getEducations()){
            sb.append(education.getInstitution())
                    .append(" ")
                    .append(education.getMajor())
                    .append(" ")
                    .append(education.getDegree())
                    .append(" ");
        }
        memberDto.setEducations(sb.toString());
        return memberDto;
    }

    // Getters and Setters

    @Override
    public String toString() {
        return email + " " +
                name + " " +
                nickname + " " +
                careers + " " +
                certifications + " " +
                educations + " " +
                interests + " " +
                skills;
    }
}
