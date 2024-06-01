package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.post.domain.Application;
import com.cbnu.teammatching.member.dto.MemberSignUpRequest;
import com.cbnu.teammatching.message.domain.Message;
import com.cbnu.teammatching.post.domain.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

    @NotNull
    private LocalDate birthdate;

    @NotNull
    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private RoleType role;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Career> careers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Certification> certifications = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "recipient")
    private List<Message> receivedMessages = new ArrayList<>();

    @OneToMany(mappedBy = "applicant")
    private List<Application> applications = new ArrayList<>();

    public static Member createMember(MemberSignUpRequest registrationDto) {
        Member member = new Member();
        member.password = registrationDto.getPassword();
        member.nickname = registrationDto.getNickname();
        member.name = registrationDto.getName();
        member.email = registrationDto.getEmail();
        member.birthdate = registrationDto.getBirthdate();
        member.phoneNumber = registrationDto.getPhoneNumber();
        member.role = RoleType.USER;
        return member;
    }

}