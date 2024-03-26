package com.cbnu.teammatching.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NotNull
public class MemberSignUpRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @Email
    @NotNull
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Pattern(regexp = "^010\\d{8}$", message = "올바른 전화번호를 입력해 주세요")
    @NotBlank
    private String phoneNumber;

    public void encodePassword(String password) {
        this.password = password;
    }
}
