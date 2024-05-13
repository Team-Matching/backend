package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.member.dto.InterestRequest;
import com.cbnu.teammatching.member.dto.SkillRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Long id;

    @Column(unique = true)
    private String interest;

    @OneToMany(mappedBy = "interest", cascade = CascadeType.ALL)
    private List<MemberInterest> memberInterests = new ArrayList<>();

    public static Interest createInterest(InterestRequest.InterestDto interestDto, MemberInterest... memberInterests) {
        Interest interest = new Interest();
        interest.interest = interestDto.getInterest();
        for (MemberInterest memberInterest : memberInterests) {
            interest.addMemberInterest(memberInterest);
        }
        return interest;
    }

    public void addMemberInterest(MemberInterest memberInterest) {
        memberInterests.add(memberInterest);
        memberInterest.setInterest(this);
    }
}
