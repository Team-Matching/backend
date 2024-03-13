package com.cbnu.teammatching.application.domain;

import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.post.domain.Post;
import jakarta.persistence.*;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicantId")
    private Member applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    private String reason;
    private String request;
    private String description;
}
