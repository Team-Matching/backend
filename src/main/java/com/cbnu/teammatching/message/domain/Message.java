package com.cbnu.teammatching.message.domain;

import com.cbnu.teammatching.member.domain.Member;
import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipientId")
    private Member recipient;

    private String title;
    private String content;
}
