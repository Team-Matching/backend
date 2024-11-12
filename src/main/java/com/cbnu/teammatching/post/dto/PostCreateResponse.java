package com.cbnu.teammatching.post.dto;

import com.cbnu.teammatching.post.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostCreateResponse {

    private String title;

    private LocalDateTime creationDate;

    public static PostCreateResponse of(Post post) {
        PostCreateResponse response = new PostCreateResponse();
        response.title = post.getTitle();
        response.creationDate = post.getCreationDate();
        return response;
    }
}
