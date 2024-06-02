package com.cbnu.teammatching.post.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.post.dto.*;
import com.cbnu.teammatching.post.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "게시글 API", description = "게시글 CRUD 관련 API")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<PostCreateResponse>> createPost(@RequestBody PostCreateRequest request) {
        PostCreateResponse response = postService.createPost(request);
        return ApiResponse.success(POST_CREATE_SUCCESS, response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable Long postId) {
        PostResponse response = postService.getPost(postId);
        return ApiResponse.success(RETRIEVAL_SUCCESS, response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostSummaryDto>>> getPostsByCategory(@RequestParam(required = false) String categoryName) {
        List<PostSummaryDto> response = postService.getPosts(categoryName);
        return ApiResponse.success(RETRIEVAL_SUCCESS, response);
    }
}
