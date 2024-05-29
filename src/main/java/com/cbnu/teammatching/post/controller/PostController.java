package com.cbnu.teammatching.post.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.common.response.ApiSuccessStatus;
import com.cbnu.teammatching.post.dto.PostCreateRequest;
import com.cbnu.teammatching.post.dto.PostCreateResponse;
import com.cbnu.teammatching.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<PostCreateResponse>> createPost(@RequestBody PostCreateRequest request) {
        PostCreateResponse response = postService.createPost(request);
        return ApiResponse.success(SIGNUP_SUCCESS, response);
    }
}
