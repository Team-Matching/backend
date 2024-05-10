package com.cbnu.teammatching.post.domain;

import com.cbnu.teammatching.application.domain.Application;
import com.cbnu.teammatching.category.domain.Category;
import com.cbnu.teammatching.member.domain.Member;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member member;

    private LocalDateTime creationDate;

    private int teamMemberCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String content;
    private boolean isDeleted;

    @OneToMany(mappedBy = "post")
    private List<Application> applications;

    @Enumerated(EnumType.STRING)
    private PostStatus status;
}