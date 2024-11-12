package com.cbnu.teammatching.post.service;

import com.cbnu.teammatching.application.domain.ApplicationStatus;
import com.cbnu.teammatching.category.domain.Category;
import com.cbnu.teammatching.category.repository.CategoryRepository;
import com.cbnu.teammatching.exception.category.CategoryNotFoundException;
import com.cbnu.teammatching.exception.member.MemberNotFoundException;
import com.cbnu.teammatching.exception.post.PostDeletedException;
import com.cbnu.teammatching.exception.post.PostNotFoundException;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.repository.MemberRepository;
import com.cbnu.teammatching.post.domain.Post;
import com.cbnu.teammatching.post.dto.PostCreateRequest;
import com.cbnu.teammatching.post.dto.PostCreateResponse;
import com.cbnu.teammatching.post.dto.PostResponse;
import com.cbnu.teammatching.post.dto.PostSummaryDto;
import com.cbnu.teammatching.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public PostCreateResponse createPost(PostCreateRequest request) {

        Member member = memberRepository.findById(JwtUtil.getMemberIdFromToken())
                .orElseThrow(MemberNotFoundException::new);

        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(CategoryNotFoundException::new);

        Post post = Post.createPost(member, category, request);

        postRepository.save(post);

        return PostCreateResponse.of(post);
    }

    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (post.isDeleted()) {
            throw new PostDeletedException();
        }

        List<String> teamMemberEmails = post.getApplications().stream()
                .filter(application -> application.getStatus()
                        .equals(ApplicationStatus.Accepted))
                .map(application -> application.getApplicant().getEmail())
                .toList();

        return PostResponse.of(post, teamMemberEmails);
    }

    public List<PostSummaryDto> getPosts(String categoryName) {
        if (categoryName != null) {
            return getPostsByCategory(categoryName);
        }
        return postRepository.findAllByOrderByCreationDateDesc().stream()
                .map(PostSummaryDto::of)
                .toList();
    }

    public List<PostSummaryDto> getPostsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(CategoryNotFoundException::new);

        return postRepository.findByCategoryOrderByCreationDateDesc(category).stream()
                .map(PostSummaryDto::of)
                .toList();
    }

    public List<PostSummaryDto> getPostsByMember() {
        Member member = memberRepository.findById(JwtUtil.getMemberIdFromToken())
                .orElseThrow(MemberNotFoundException::new);
        List<Post> posts = postRepository.findByMemberId(member.getId());
        return posts.stream()
                .map(PostSummaryDto::of)
                .toList();
    }
}
