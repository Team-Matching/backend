package com.cbnu.teammatching.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiSuccessStatus {
    SIGNUP_SUCCESS(HttpStatus.CREATED,"회원가입 성공"),
    SIGNIN_SUCCESS(HttpStatus.ACCEPTED, "로그인 성공"),
    PROFILE_CAREER_SAVE(HttpStatus.CREATED,"이력 등록 성공"),
    RETRIEVAL_SUCCESS(HttpStatus.OK,"조회 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
