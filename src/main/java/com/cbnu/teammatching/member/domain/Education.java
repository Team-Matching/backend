package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.member.dto.EducationDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String institution;

    private String degree;

    private String major;

    private LocalDate startYear;

    private LocalDate endYear;

    public static Education createEducation(Member member, EducationDto dto) {
        Education education = new Education();
        education.member = member;
        education.institution = dto.getInstitution();
        education.degree = dto.getDegree();
        education.major = dto.getMajor();
        education.startYear = dto.getStartYear();
        education.endYear = dto.getEndYear();
        member.getEducations().add(education);
        return education;
    }

    public void updateEducation(EducationDto update) {
        this.institution = update.getInstitution();
        this.degree = update.getDegree();
        this.major = update.getMajor();
        this.startYear = update.getStartYear();
        this.endYear = update.getEndYear();
    }
}
