package com.cbnu.teammatching.post.domain;

import com.cbnu.teammatching.member.domain.Member;
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

    private String description;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    public static Application createApplication(Member applicant, Post post, String description) {
        Application application = new Application();
        application.applicant = applicant;
        application.post = post;
        application.description = description;
        application.status = ApplicationStatus.Pending;
        applicant.getApplications().add(application);
        post.getApplications().add(application);
        return application;
    }

    public void processByStatus(String status) {
        switch (status) {
            case "accept" -> this.status = ApplicationStatus.Accepted;
            case "reject" -> this.status = ApplicationStatus.Rejected;
            case "pending" -> this.status = ApplicationStatus.Pending;
            default -> throw new IllegalArgumentException("올바르지 않은 상태값입니다.");
        }
    }
}
