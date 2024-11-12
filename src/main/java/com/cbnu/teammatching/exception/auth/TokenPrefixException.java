package com.cbnu.teammatching.exception.auth;

import com.cbnu.teammatching.common.response.ApiErrorStatus;
import com.cbnu.teammatching.exception.ApiException;
import org.springframework.http.HttpStatus;

public class TokenPrefixException extends ApiException {

    private static final ApiErrorStatus ERROR_STATUS = ApiErrorStatus.INVALID_TOKEN_PREFIX;

    public TokenPrefixException() {
        this(ERROR_STATUS.getCODE(), ERROR_STATUS.getMESSAGE());
    }

    protected TokenPrefixException(String errorCode, String message) {
        super(errorCode, HttpStatus.BAD_REQUEST, message);
    }
}
