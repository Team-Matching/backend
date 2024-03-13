package com.cbnu.teammatching.member.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String certificationName;
    private String issuer;

    private LocalDateTime dateObtained;
}
