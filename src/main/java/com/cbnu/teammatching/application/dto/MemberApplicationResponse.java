package com.cbnu.teammatching.application.dto;

import com.cbnu.teammatching.application.domain.Application;
import com.cbnu.teammatching.application.domain.ApplicationStatus;
import lombok.Getter;

@Getter
public class MemberApplicationResponse {

    private String postTitle;

    private String applicationTitle;

    private String description;

    private ApplicationStatus status;

    public static MemberApplicationResponse of(Application application) {
        MemberApplicationResponse response = new MemberApplicationResponse();
        response.postTitle = application.getPost().getTitle();
        response.applicationTitle = application.getTitle();
        response.description = application.getDescription();
        response.status = application.getStatus();
        return response;
    }
}
