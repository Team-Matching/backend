package com.cbnu.teammatching.exception.member;

import com.cbnu.teammatching.common.response.ApiErrorStatus;
import com.cbnu.teammatching.exception.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidEmailOrPasswordException extends ApiException {
    private static final ApiErrorStatus ERROR_STATUS = ApiErrorStatus.INVALID_ID_OR_PASSWORD;

    public InvalidEmailOrPasswordException() {
        this(ERROR_STATUS.getCODE(), ERROR_STATUS.getMESSAGE());
    }

    protected InvalidEmailOrPasswordException(String errorCode, String message) {
        super(errorCode, HttpStatus.BAD_REQUEST, message);
    }
}
