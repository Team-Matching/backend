package com.cbnu.teammatching.member.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String interest;
}
