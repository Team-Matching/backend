package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Member;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MyProfileResponse {
    private String email;

    private String name;

    private String nickname;

    private LocalDate birthdate;

    private String phoneNumber;

    public static MyProfileResponse of(Member member) {
        MyProfileResponse myProfileResponse = new MyProfileResponse();
        myProfileResponse.email = member.getEmail();
        myProfileResponse.name = member.getName();
        myProfileResponse.nickname = member.getNickname();
        myProfileResponse.birthdate = member.getBirthdate();
        myProfileResponse.phoneNumber = member.getPhoneNumber();
        return myProfileResponse;
    }
}
