package com.cbnu.teammatching.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiErrorStatus {

    DUPLICATED_MEMBER_FIELD("MEM_0001", "이미 사용중인 아이디 또는 이메일, 전화번호 입니다"),
    INVALID_ID_OR_PASSWORD("MEM_0002","아이디 또는 비밀번호 오류입니다");

    private final String CODE;
    private final String MESSAGE;
}
