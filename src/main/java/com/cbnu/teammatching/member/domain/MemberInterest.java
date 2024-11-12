package com.cbnu.teammatching.member.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_id")
    private Interest interest;

    public static MemberInterest createMemberInterest(Member member) {
        MemberInterest memberInterest = new MemberInterest();
        memberInterest.member = member;
        return memberInterest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }
}
