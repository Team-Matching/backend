package com.cbnu.teammatching.common.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Builder
@Data
public class ApiResponse<T> {

    private final String message;
    private final T body;

    public static <T> ResponseEntity<ApiResponse<T>> success(ApiSuccessStatus status, T data) {
        return ResponseEntity.status(status.getHttpStatus())
                .body(ApiResponse.<T>builder()
                        .message(status.getMessage())
                        .body(data)
                        .build());
    }

}
