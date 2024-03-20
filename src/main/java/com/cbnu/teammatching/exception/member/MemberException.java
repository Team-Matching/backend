package com.cbnu.teammatching.exception.member;

import com.cbnu.teammatching.exception.ApiException;
import org.springframework.http.HttpStatus;

public class MemberException extends ApiException {

    protected MemberException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
