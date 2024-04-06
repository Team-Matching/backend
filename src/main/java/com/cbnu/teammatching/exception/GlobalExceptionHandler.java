package com.cbnu.teammatching.exception;

import com.cbnu.teammatching.common.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException e) {
        return ApiErrorResponse.fail(e.getErrorCode(), e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> allErrors = e.getFieldErrors();
        String message = allErrors.stream().map(error -> String.format("%s: %s, ", error.getField(), error.getDefaultMessage())).collect(Collectors.joining());
        if (message.endsWith(", ")) {
            message = message.substring(0, message.length() - 2);
        }
        return ApiErrorResponse.fail("Validation", message, HttpStatus.BAD_REQUEST);
    }
}
