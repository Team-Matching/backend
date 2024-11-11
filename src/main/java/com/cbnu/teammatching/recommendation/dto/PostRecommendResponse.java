package com.cbnu.teammatching.recommendation.dto;

import com.cbnu.teammatching.post.domain.Post;
import lombok.Getter;

@Getter
public class PostRecommendResponse {

    private Long postId;

    private String title;

    private String detail;

    private String summary;

    private double similarity;

    public static PostRecommendResponse of(Post post, double similarity) {
        PostRecommendResponse response = new PostRecommendResponse();
        response.postId = post.getId();
        response.title = post.getTitle();
        response.detail = post.getDetail();
        response.summary = post.getSummary();
        response.similarity = similarity;
        return response;
    }
}
