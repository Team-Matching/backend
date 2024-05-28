package com.cbnu.teammatching.post.service;

import com.cbnu.teammatching.category.domain.Category;
import com.cbnu.teammatching.category.repository.CategoryRepository;
import com.cbnu.teammatching.common.response.ApiErrorStatus;
import com.cbnu.teammatching.exception.category.CategoryNotFoundException;
import com.cbnu.teammatching.exception.member.MemberNotFoundException;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.repository.MemberRepository;
import com.cbnu.teammatching.post.domain.Post;
import com.cbnu.teammatching.post.dto.PostCreateRequest;
import com.cbnu.teammatching.post.dto.PostCreateResponse;
import com.cbnu.teammatching.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public PostCreateResponse createPost(PostCreateRequest request) {

        Member member = memberRepository.findById(JwtUtil.getMemberIdFromToken())
                .orElseThrow(MemberNotFoundException::new);

        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(CategoryNotFoundException::new);

        Post post = Post.createPost(member, category, request);

        postRepository.save(post);

        return PostCreateResponse.of(post);
    }
}
