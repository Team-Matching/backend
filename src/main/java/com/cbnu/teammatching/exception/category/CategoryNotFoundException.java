package com.cbnu.teammatching.exception.category;

import com.cbnu.teammatching.common.response.ApiErrorStatus;
import com.cbnu.teammatching.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiException {

    private static final ApiErrorStatus ERROR_STATUS = ApiErrorStatus.CATEGORY_NOT_FOUND;

    public CategoryNotFoundException() {
        this(ERROR_STATUS.getCODE(), ERROR_STATUS.getMESSAGE());
    }
    protected CategoryNotFoundException(String errorCode, String message) {
        super(errorCode, HttpStatus.NOT_FOUND, message);
    }
}
