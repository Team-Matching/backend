package com.cbnu.teammatching.recommendation.service;

import com.cbnu.teammatching.exception.member.MemberNotFoundException;
import com.cbnu.teammatching.exception.post.PostNotFoundException;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.domain.Interest;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.domain.Skill;
import com.cbnu.teammatching.member.repository.MemberInterestRepository;
import com.cbnu.teammatching.member.repository.MemberRepository;
import com.cbnu.teammatching.member.repository.MemberSkillRepository;
import com.cbnu.teammatching.post.domain.Post;
import com.cbnu.teammatching.post.repository.PostRepository;
import com.cbnu.teammatching.recommendation.dto.MemberDto;
import com.cbnu.teammatching.recommendation.dto.PostRecommendResponse;
import com.cbnu.teammatching.recommendation.dto.PostSimilarity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RestTemplate restTemplate;

    private final PostRepository postRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final MemberInterestRepository memberInterestRepository;
    private final MemberRepository memberRepository;

    public List<PostRecommendResponse> getRecommendations() {
        String url = "https://prime-sloth-properly.ngrok-free.app/recommend-post";
        Long currentMemberId = JwtUtil.getMemberIdFromToken();
        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(MemberNotFoundException::new);
        List<Skill> skillsByMemberId = memberSkillRepository.findSkillsByMemberId(currentMemberId);
        List<Interest> interestsByMemberId = memberInterestRepository.findInterestsByMemberId(currentMemberId);
        MemberDto memberDto = MemberDto.of(member, skillsByMemberId, interestsByMemberId);

        String jsonRequest = "{\"user_profile\":\"" + memberDto.toString() + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(jsonRequest);

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray similarityArray = jsonResponse.getJSONArray("similarity").getJSONArray(0);

        List<PostSimilarity> postSimilarities = new ArrayList<>();

        // 유사도가 0보다 큰 경우만 리스트에 추가
        for (int i = 0; i < similarityArray.length(); i++) {
            double similarity = similarityArray.getDouble(i);
            if (similarity > 0) {  // 유사도가 0보다 큰 경우만 추가
                long postId = i + 1L;
                // 해당 게시글이 존재하는지 먼저 확인
                Optional<Post> postOptional = postRepository.findById(postId);
                if (postOptional.isPresent()) {
                    Post post = postOptional.get();
                    // 자신이 작성한 게시글이 아닌 경우만 추가
                    if (!post.getMember().getId().equals(currentMemberId)) {
                        postSimilarities.add(new PostSimilarity(postId, similarity));
                    }
                }
            }
        }

        // 유사도 기준 내림차순 정렬
        postSimilarities.sort((a, b) -> Double.compare(b.similarity(), a.similarity()));

        // 상위 3개 추천 결과 생성
        List<PostRecommendResponse> recommendations = new ArrayList<>();
        for (int i = 0; i < Math.min(3, postSimilarities.size()); i++) {
            PostSimilarity ps = postSimilarities.get(i);
            Post post = postRepository.findById(ps.postId())
                    .orElseThrow(PostNotFoundException::new);
            recommendations.add(PostRecommendResponse.of(post, ps.similarity()));
        }

        return recommendations;
    }
}
