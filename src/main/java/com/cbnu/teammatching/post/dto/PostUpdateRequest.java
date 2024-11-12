package com.cbnu.teammatching.post.dto;

import lombok.Getter;

@Getter
public class PostUpdateRequest {
    private String title;

    private int teamMemberCount;

    private String detail;

    private String summary;

    private String category;
}
