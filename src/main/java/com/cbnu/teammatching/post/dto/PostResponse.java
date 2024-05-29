package com.cbnu.teammatching.post.dto;

import com.cbnu.teammatching.post.domain.Post;
import com.cbnu.teammatching.post.domain.PostStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponse {

    private String title;

    //작성자 이메일
    private String authorEmail;

    //팀원 이메일
    private List<String> teamMemberEmails;

    private LocalDateTime creationDate;

    private int teamMemberCount;

    private int currentMemberCount;

    private String category;

    private String detail;

    private PostStatus status;

    public static PostResponse of(Post post, List<String> emails){
        PostResponse postResponse = new PostResponse();
        postResponse.title = post.getTitle();
        postResponse.authorEmail = post.getMember().getEmail();
        postResponse.teamMemberEmails = emails;
        postResponse.creationDate = post.getCreationDate();
        postResponse.teamMemberCount = post.getTeamMemberCount();
        postResponse.currentMemberCount = post.getApplications().size();
        postResponse.category = post.getCategory().getName();
        postResponse.detail = post.getDetail();
        postResponse.status = post.getStatus();
        return postResponse;
    }
}
