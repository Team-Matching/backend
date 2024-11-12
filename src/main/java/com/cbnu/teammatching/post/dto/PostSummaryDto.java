package com.cbnu.teammatching.post.dto;

import com.cbnu.teammatching.application.domain.ApplicationStatus;
import com.cbnu.teammatching.post.domain.Post;
import com.cbnu.teammatching.post.domain.PostStatus;
import lombok.Getter;

@Getter
public class PostSummaryDto {

    private Long postId;

    private String title;

    private String authorEmail;

    private PostStatus postStatus;

    private int teamMemberCount;

    private int currentMemberCount;

    private String summary;

    private String category;

    public static PostSummaryDto of(Post post) {
        PostSummaryDto postSummaryDto = new PostSummaryDto();
        postSummaryDto.postId = post.getId();
        postSummaryDto.title = post.getTitle();
        postSummaryDto.authorEmail = post.getMember().getEmail();
        postSummaryDto.postStatus = post.getStatus();
        postSummaryDto.teamMemberCount = post.getTeamMemberCount();
        postSummaryDto.currentMemberCount = (int)post.getApplications().stream()
                .filter(application -> application.getStatus()
                        .equals(ApplicationStatus.Accepted)).count();
        postSummaryDto.summary = post.getSummary();
        postSummaryDto.category = post.getCategory().getName();
        return postSummaryDto;
    }
}
