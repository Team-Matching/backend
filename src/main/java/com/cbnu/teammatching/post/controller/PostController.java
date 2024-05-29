package com.cbnu.teammatching.post.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.common.response.ApiSuccessStatus;
import com.cbnu.teammatching.post.dto.PostCreateRequest;
import com.cbnu.teammatching.post.dto.PostCreateResponse;
import com.cbnu.teammatching.post.dto.PostResponse;
import com.cbnu.teammatching.post.dto.PostSummaryDto;
import com.cbnu.teammatching.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
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
    public ResponseEntity<ApiResponse<List<PostSummaryDto>>> getPosts() {
        List<PostSummaryDto> response = postService.getPosts();
        return ApiResponse.success(RETRIEVAL_SUCCESS, response);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<ApiResponse<List<PostSummaryDto>>> getPostsByCategory(@PathVariable String categoryName) {
        List<PostSummaryDto> response = postService.getPostsByCategory(categoryName);
        return ApiResponse.success(RETRIEVAL_SUCCESS, response);
    }
}
