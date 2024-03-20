package com.cbnu.teammatching.exception.dto;

import com.cbnu.teammatching.exception.ApiException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ApiErrorResponse {
    private int status;
    private String errorCode;
    private String message;

    public static ResponseEntity<ApiErrorResponse> toResponseEntity(ApiException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ApiErrorResponse.builder()
                        .status(e.getHttpStatus().value())
                        .errorCode(e.getErrorCode())
                        .message(e.getMessage())
                        .build());
    }
}
