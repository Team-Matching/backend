package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Career;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CareerDto {
    private Long careerId;

    private String company;

    private String role;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    public static CareerDto of(Career career) {
        return new CareerDto(career.getId(), career.getCompany(), career.getRole(), career.getStartDate(), career.getEndDate(), career.getDescription());
    }
}
