package com.cbnu.teammatching.application.domain;

import com.cbnu.teammatching.application.dto.PostApplyRequest;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.post.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Member applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    public static Application createApplication(Member applicant, Post post, PostApplyRequest request) {
        Application application = new Application();
        application.applicant = applicant;
        application.post = post;
        application.description = request.getDescription();
        application.title = request.getTitle();
        application.status = ApplicationStatus.Pending;
        applicant.getApplications().add(application);
        post.getApplications().add(application);
        return application;
    }

    public void processByStatus(String status) {
        switch (status) {
            case "Accepted" -> this.status = ApplicationStatus.Accepted;
            case "Rejected" -> this.status = ApplicationStatus.Rejected;
            case "Pending" -> this.status = ApplicationStatus.Pending;
            default -> throw new IllegalArgumentException("올바르지 않은 상태값입니다.");
        }
    }
}
