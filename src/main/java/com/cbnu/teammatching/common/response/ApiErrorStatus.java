package com.cbnu.teammatching.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiErrorStatus {

    //Member Exception
    DUPLICATED_MEMBER_FIELD("MEM_001", "이미 사용중인 아이디 또는 이메일, 전화번호 입니다"),
    INVALID_ID_OR_PASSWORD("MEM_002","아이디 또는 비밀번호 오류입니다"),
    MEMBER_NOT_FOUND("MEM_003","회원을 찾을 수 없습니다"),
    INVALID_TOKEN_PREFIX("AUTH_001","허용되지 않은 토큰입니다"),
    CATEGORY_NOT_FOUND("CATE_001","카테고리를 찾을 수 없습니다"),
    POST_NOT_FOUND("POST_001","게시글을 찾을 수 없습니다"),
    POST_DELETED("POST_002","삭제된 게시글입니다");


    private final String CODE;
    private final String MESSAGE;
}
