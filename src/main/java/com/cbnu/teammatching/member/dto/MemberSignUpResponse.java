package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignUpResponse {
    private Long id;
    private String email;
    private String name;
    private String nickname;

    public static MemberSignUpResponse of(Member member) {
        return new MemberSignUpResponse(member.getId(), member.getEmail(), member.getName(),member.getNickname());
    }
}


