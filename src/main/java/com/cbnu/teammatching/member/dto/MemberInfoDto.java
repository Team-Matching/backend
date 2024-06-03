package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberInfoDto {

    private Long memberId;

    private String email;

    private String nickname;

    public static MemberInfoDto of(Member member) {
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        memberInfoDto.memberId = member.getId();
        memberInfoDto.email = member.getEmail();
        memberInfoDto.nickname = member.getNickname();
        return memberInfoDto;
    }
}
