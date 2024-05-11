package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.member.dto.InterestRequest;
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

    @Column(unique = true)
    private String interest;

    public static Interest createInterest(Member member, InterestRequest.InterestDto interestDto) {
        Interest interest = new Interest();
        interest.member = member;
        interest.interest = interestDto.getInterest();
        member.getInterests().add(interest);
        return interest;
    }
}
