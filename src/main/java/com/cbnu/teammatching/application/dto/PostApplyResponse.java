package com.cbnu.teammatching.application.dto;

import com.cbnu.teammatching.application.domain.Application;
import com.cbnu.teammatching.application.domain.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostApplyResponse {

    private String applicantEmail;

    private Long postId;

    private String title;

    private String description;

    private ApplicationStatus status;

    public static PostApplyResponse of(Application application) {
        PostApplyResponse postApplyResponse = new PostApplyResponse();
        postApplyResponse.applicantEmail = application.getApplicant().getEmail();
        postApplyResponse.postId = application.getPost().getId();
        postApplyResponse.description = application.getDescription();
        postApplyResponse.title = application.getTitle();
        postApplyResponse.status = application.getStatus();
        return postApplyResponse;
    }
}
