package com.cbnu.teammatching.post.dto;

import com.cbnu.teammatching.post.domain.Application;
import com.cbnu.teammatching.post.domain.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostApplyResponse {

    private String applicantEmail;

    private Long postId;

    private String description;

    private ApplicationStatus status;

    public static PostApplyResponse of(Application application) {
        PostApplyResponse postApplyResponse = new PostApplyResponse();
        postApplyResponse.applicantEmail = application.getApplicant().getEmail();
        postApplyResponse.postId = application.getPost().getId();
        postApplyResponse.description = application.getDescription();
        postApplyResponse.status = application.getStatus();
        return postApplyResponse;
    }
}
