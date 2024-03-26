package com.cbnu.teammatching.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiErrorStatus {

    DUPLICATED_MEMBER_FIELD("MEM_0001", "이미 사용중인 아이디 또는 이메일, 전화번호 입니다");

    private final String CODE;
    private final String MESSAGE;
}
