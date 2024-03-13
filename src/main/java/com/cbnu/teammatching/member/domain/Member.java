package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.application.domain.Application;
import com.cbnu.teammatching.message.domain.Message;
import com.cbnu.teammatching.post.domain.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private String id;

    private String password;
    private String nickname;
    private String email;

    private LocalDateTime birthdate;

    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @OneToMany(mappedBy = "member")
    private List<Interest> interests;

    @OneToMany(mappedBy = "member")
    private List<Skill> skills;

    @OneToMany(mappedBy = "member")
    private List<Career> careers;

    @OneToMany(mappedBy = "member")
    private List<Certification> certifications;

    @OneToMany(mappedBy = "member")
    private List<Education> educations;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "recipient")
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "applicant")
    private List<Application> applications;
}