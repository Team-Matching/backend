package com.cbnu.teammatching.exception.member;

import com.cbnu.teammatching.common.response.ApiErrorStatus;
import com.cbnu.teammatching.exception.ApiException;
import org.springframework.http.HttpStatus;

public class DuplicatedMemberFieldException extends ApiException {
    private static final ApiErrorStatus ERROR_STATUS = ApiErrorStatus.DUPLICATED_MEMBER_FIELD;

    public DuplicatedMemberFieldException() {
        this(ERROR_STATUS.getCODE(), ERROR_STATUS.getMESSAGE());
    }

    private DuplicatedMemberFieldException(String errorCode, String message) {
        super(errorCode,HttpStatus.BAD_REQUEST,message);
    }
}
