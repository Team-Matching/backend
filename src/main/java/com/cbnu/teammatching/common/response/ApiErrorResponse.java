package com.cbnu.teammatching.common.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ApiErrorResponse {
    String errorCode;
    String message;

    public static ResponseEntity<ApiErrorResponse> fail(String errorCode, String message, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(ApiErrorResponse.builder()
                        .errorCode(errorCode)
                        .message(message)
                        .build());
    }
}
