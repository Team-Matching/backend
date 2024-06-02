package com.cbnu.teammatching.application.controller;

import com.cbnu.teammatching.application.dto.MemberApplicationResponse;
import com.cbnu.teammatching.application.service.MemberApplicationService;
import com.cbnu.teammatching.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "회원 지원서 API", description = "회원 지원서 관련 API")
public class MemberApplicationController {

    private final MemberApplicationService memberApplicationService;

    @GetMapping("/applications")
    public ResponseEntity<ApiResponse<List<MemberApplicationResponse>>> getApplications() {
        return ApiResponse.success(RETRIEVAL_SUCCESS,memberApplicationService.getApplications());
    }
}
