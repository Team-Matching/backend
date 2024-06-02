package com.cbnu.teammatching.post.service;

import com.cbnu.teammatching.post.domain.Application;
import com.cbnu.teammatching.post.dto.PostApplyRequest;
import com.cbnu.teammatching.post.dto.PostApplyResponse;
import com.cbnu.teammatching.post.repository.ApplicationRepository;
import com.cbnu.teammatching.exception.member.MemberNotFoundException;
import com.cbnu.teammatching.exception.post.PostNotFoundException;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.repository.MemberRepository;
import com.cbnu.teammatching.post.domain.Post;
import com.cbnu.teammatching.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;

    public PostApplyResponse apply(Long postId, PostApplyRequest postApplyRequest) {

        Member member = memberRepository.findById(JwtUtil.getMemberIdFromToken())
                .orElseThrow(MemberNotFoundException::new);

        Post post = getPostById(postId);

        if(post.getMember().equals(member)){
            throw new IllegalArgumentException("팀장은 자신의 모집공고에 지원할 수 없습니다.");
        }

        if (applicationRepository.existsByApplicantIdAndPostId(member.getId(), post.getId())) {
            throw new IllegalArgumentException("이미 지원한 모집공고입니다.");
        }

        Application application = Application.createApplication(member, post, postApplyRequest.getDescription());
        applicationRepository.save(application);
        return PostApplyResponse.of(application);
    }

    public List<PostApplyResponse> getAppliedMembers(Long postId) {

        Post post = getPostById(postId);
        if(!post.getMember().getId().equals(JwtUtil.getMemberIdFromToken())){
            throw new IllegalArgumentException("해당 모집공고의 팀장이 아닙니다.");
        }
        return post.getApplications().stream()
                .map(PostApplyResponse::of)
                .toList();
    }

    public PostApplyResponse updateApplicationStatus(Long postId, String memberEmail, String status) {

        Post post = getPostById(postId);
        if (!post.getMember().getId().equals(JwtUtil.getMemberIdFromToken())) {
            throw new IllegalArgumentException("해당 모집공고의 팀장이 아닙니다.");
        }

        Application application = applicationRepository.findByApplicantEmailAndPostId(memberEmail, postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 지원한 모집공고가 없습니다."));

        application.processByStatus(status);
        applicationRepository.save(application);
        return PostApplyResponse.of(application);
    }

    private Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
    }


}
