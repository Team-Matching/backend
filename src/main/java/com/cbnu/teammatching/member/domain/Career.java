package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.member.dto.CareerDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String company;
    private String role;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    public static Career createCareer(Member member, CareerDto request) {
        Career career = new Career();
        career.member = member;
        career.company = request.getCompany();
        career.role = request.getRole();
        career.startDate = request.getStartDate();
        career.endDate = request.getEndDate();
        career.description = request.getDescription();
        member.getCareers().add(career);
        return career;
    }

    public void updateCareer(CareerDto update) {
        this.company = update.getCompany();
        this.role = update.getRole();
        this.startDate = update.getStartDate();
        this.endDate = update.getEndDate();
        this.description = update.getDescription();
    }
}
