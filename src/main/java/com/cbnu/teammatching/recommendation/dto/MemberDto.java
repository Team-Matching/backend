package com.cbnu.teammatching.recommendation.dto;


import com.cbnu.teammatching.member.domain.Career;
import com.cbnu.teammatching.member.domain.Certification;
import com.cbnu.teammatching.member.domain.Education;
import com.cbnu.teammatching.member.domain.Interest;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.domain.Skill;
import java.util.List;
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
    private String skills;
    private String interests;

    public static MemberDto of(Member member, List<Skill> skillsByMemberId, List<Interest> interestsByMemberId) {
        StringBuilder sb = new StringBuilder();
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(member.getEmail());
        memberDto.setName(member.getName());
        memberDto.setNickname(member.getNickname());

        // Careers
        for (Career career : member.getCareers()) {
            sb.append(career.getRole()).append(" ")
                    .append(career.getCompany()).append(" ")
                    .append(career.getDescription()).append(" ");
        }
        memberDto.setCareers(sb.toString().trim());
        sb.setLength(0);  // StringBuilder 초기화

        // Certifications
        for (Certification certification : member.getCertifications()) {
            sb.append(certification.getCertificationName()).append(" ");
        }
        memberDto.setCertifications(sb.toString().trim());
        sb.setLength(0);  // StringBuilder 초기화

        // Educations
        for (Education education : member.getEducations()) {
            sb.append(education.getInstitution()).append(" ")
                    .append(education.getDegree()).append(" ")
                    .append(education.getMajor()).append(" ");
        }
        memberDto.setEducations(sb.toString().trim());
        sb.setLength(0);  // StringBuilder 초기화

        // Skills
        for (Skill skill : skillsByMemberId) {
            sb.append(skill.getSkill()).append(" ");
        }
        memberDto.setSkills(sb.toString().trim());
        sb.setLength(0);  // StringBuilder 초기화

        // Interests
        for (Interest interest : interestsByMemberId) {
            sb.append(interest.getInterest()).append(" ");
        }
        memberDto.setInterests(sb.toString().trim());

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
