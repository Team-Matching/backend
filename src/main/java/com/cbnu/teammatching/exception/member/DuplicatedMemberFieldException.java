package com.cbnu.teammatching.exception.member;

import org.springframework.http.HttpStatus;

import java.util.List;

public class DuplicatedMemberFieldException extends MemberException{
    private static final String CODE = "M001";
    private static final String MESSAGE = "이미 사용중인 ";

    public DuplicatedMemberFieldException(List<String> fieldNames) {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE + String.join(", ",fieldNames));
    }
}
