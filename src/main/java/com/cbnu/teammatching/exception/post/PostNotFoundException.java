package com.cbnu.teammatching.exception.post;

import com.cbnu.teammatching.common.response.ApiErrorStatus;
import com.cbnu.teammatching.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends ApiException {
    private static final ApiErrorStatus ERROR_STATUS = ApiErrorStatus.POST_NOT_FOUND;

    public PostNotFoundException() {
        this(ERROR_STATUS.getCODE(), ERROR_STATUS.getMESSAGE());
    }

    protected PostNotFoundException(String errorCode, String message) {
        super(errorCode, HttpStatus.NOT_FOUND, message);
    }
}
