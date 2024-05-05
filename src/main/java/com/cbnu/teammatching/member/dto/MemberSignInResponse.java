package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignInResponse {

    private String email;

    private String token;
}
