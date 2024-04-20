package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.RoleType;
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
