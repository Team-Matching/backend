package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignInResponse {
    private String username;

    public static MemberSignInResponse of(Member member) {
        return new MemberSignInResponse(member.getUsername());
    }
}
