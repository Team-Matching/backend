package com.cbnu.teammatching.post.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostCreateRequest {

    private String title;

    private LocalDateTime creationDate;

    private int teamMemberCount;

    private String category;

    private String detail;

    private String summary;

}
