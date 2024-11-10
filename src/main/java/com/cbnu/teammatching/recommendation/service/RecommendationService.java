package com.cbnu.teammatching.recommendation.service;

import com.cbnu.teammatching.exception.post.PostNotFoundException;
import com.cbnu.teammatching.post.domain.Post;
import com.cbnu.teammatching.post.repository.PostRepository;
import com.cbnu.teammatching.recommendation.dto.MemberDto;
import com.cbnu.teammatching.recommendation.dto.PostRecommendResponse;
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

    public PostRecommendResponse getRecommendations(MemberDto memberDto) {
        String url = "http://localhost:8000/recommend-post";

        // JSON 요청 본문 생성
        String jsonRequest = "{\"user_profile\":\"" + memberDto.toString() + "\"}";

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(jsonRequest);

        // HTTP 엔티티 생성
        HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

        // FastAPI 호출
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // 유사도 결과 처리
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray similarityArray = jsonResponse.getJSONArray("similarity").getJSONArray(0);

        // 가장 유사도가 높은 포스트 찾기
        double maxSimilarity = -1;
        long bestPostIndex = -1L;
        for (int i = 1; i < similarityArray.length(); i++) {
            double similarity = similarityArray.getDouble(i-1);
            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                bestPostIndex = i;
            }
        }

        Post post = postRepository.findById(bestPostIndex)
                .orElseThrow(PostNotFoundException::new);

        return PostRecommendResponse.of(post,maxSimilarity);
    }
}
