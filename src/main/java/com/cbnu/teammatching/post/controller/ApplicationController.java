package com.cbnu.teammatching.post.controller;

import com.cbnu.teammatching.post.dto.PostApplyRequest;
import com.cbnu.teammatching.post.dto.PostApplyResponse;
import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.post.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class ApplicationController {

    private final ApplicationService applicationService;

    //모집공고에 회원 지원
    @PostMapping("/{postId}/applications")
    public ResponseEntity<ApiResponse<PostApplyResponse>> apply(@PathVariable Long postId, @RequestBody PostApplyRequest postApplyRequest) {
        return ApiResponse.success(MEMBER_APPLY_SUCCESS,applicationService.apply(postId, postApplyRequest));
    }
    //모집공고에 지원한 회원 목록 조회
    @GetMapping("/{postId}/applications")
    public ResponseEntity<ApiResponse<List<PostApplyResponse>>> getAppliedMembers(@PathVariable Long postId) {
        return ApiResponse.success(RETRIEVAL_SUCCESS,applicationService.getAppliedMembers(postId));
    }

    //팀장이 지원자를 수락 또는 거절
    @PutMapping("/{postId}/applications/{memberEmail}")
    public ResponseEntity<ApiResponse<PostApplyResponse>> accept(@PathVariable Long postId, @PathVariable String memberEmail, @RequestParam String status) {;
        return ApiResponse.success(APPLICATION_PROCESS_SUCCESS,applicationService.updateApplicationStatus(postId, memberEmail,status));
    }

}

