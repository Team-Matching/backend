package com.cbnu.teammatching.member.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserInfoDto {
    private Long id;

    private String email;

    private String name;

    private String password;

    private RoleType role;
}
