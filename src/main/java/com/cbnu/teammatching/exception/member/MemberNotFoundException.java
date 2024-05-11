package com.cbnu.teammatching.exception.member;

import com.cbnu.teammatching.common.response.ApiErrorStatus;
import com.cbnu.teammatching.exception.ApiException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends ApiException {
    private static final ApiErrorStatus ERROR_STATUS = ApiErrorStatus.MEMBER_NOT_FOUND;

    public MemberNotFoundException() {
        this(ERROR_STATUS.getCODE(), ERROR_STATUS.getMESSAGE());
    }

    private MemberNotFoundException(String errorCode, String message) {
        super(errorCode,HttpStatus.BAD_REQUEST,message);
    }
}
