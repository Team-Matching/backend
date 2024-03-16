package com.cbnu.teammatching.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberRegistrationDto {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private LocalDateTime birthdate;
    private String phoneNumber;
}
