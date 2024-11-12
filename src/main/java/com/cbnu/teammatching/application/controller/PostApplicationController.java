package com.cbnu.teammatching.application.controller;

import com.cbnu.teammatching.application.dto.PostApplyRequest;
import com.cbnu.teammatching.application.dto.PostApplyResponse;
import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.application.service.PostApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Tag(name = "지원 API", description = "모집공고에 대한 지원 관련 API")
public class PostApplicationController {

    private final PostApplicationService postApplicationService;

    @Operation(summary = "지원하기", description = "모집공고에 지원합니다.")
    @PostMapping("/{postId}/applications")
    public ResponseEntity<ApiResponse<PostApplyResponse>> apply(@PathVariable Long postId, @RequestBody PostApplyRequest postApplyRequest) {
        return ApiResponse.success(MEMBER_APPLY_SUCCESS, postApplicationService.apply(postId, postApplyRequest));
    }

    @Operation(summary = "지원자 목록 조회", description = "모집공고의 지원서를 조회합니다.")
    @GetMapping("/{postId}/applications")
    public ResponseEntity<ApiResponse<List<PostApplyResponse>>> getAppliedMembers(@PathVariable Long postId) {
        return ApiResponse.success(RETRIEVAL_SUCCESS, postApplicationService.getAppliedMembers(postId));
    }

    @Operation(summary = "지원자 상태 변경", description = "팀장이 지원자를 수락 또는 거절, 대기합니다.")
    @PutMapping("/{postId}/applications/{memberEmail}")
    public ResponseEntity<ApiResponse<PostApplyResponse>> handleApplication(@PathVariable Long postId, @PathVariable String memberEmail, @RequestParam String applicationStatus) {
        return ApiResponse.success(APPLICATION_PROCESS_SUCCESS, postApplicationService.updateApplicationStatus(postId, memberEmail, applicationStatus));
    }

}

