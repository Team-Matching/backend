package com.cbnu.teammatching.member.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String description;
}
