package com.cbnu.teammatching.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignInRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
