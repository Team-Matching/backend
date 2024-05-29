package com.cbnu.teammatching.application.domain;

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

    private String description;

    private ApplicationStatus status;
}
