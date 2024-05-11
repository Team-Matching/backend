package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Education;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {

    private Long educationId;

    private String institution;

    private String degree;

    private String major;

    private LocalDate startYear;

    private LocalDate endYear;

    public static EducationDto of(Education education) {
        return new EducationDto(
                education.getId(),
                education.getInstitution(),
                education.getDegree(),
                education.getMajor(),
                education.getStartYear(),
                education.getEndYear()
        );
    }
}
