package com.cbnu.teammatching.recommendation.controller;


import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.common.response.ApiSuccessStatus;
import com.cbnu.teammatching.exception.member.MemberNotFoundException;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.repository.InterestRepository;
import com.cbnu.teammatching.member.repository.MemberRepository;
import com.cbnu.teammatching.member.repository.SkillRepository;
import com.cbnu.teammatching.recommendation.dto.MemberDto;
import com.cbnu.teammatching.recommendation.dto.PostRecommendResponse;
import com.cbnu.teammatching.recommendation.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "추천 API", description = "추천 관련 API")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final MemberRepository memberRepository;
    private final InterestRepository interestRepository;
    private final SkillRepository skillRepository;

    @PostMapping("/recommend-post")
    @Operation(summary = "팀 추천", description = "회원에게 팀을 추천합니다.")
    public ResponseEntity<ApiResponse<PostRecommendResponse>> recommendPost() {
        Member member = memberRepository.findById(JwtUtil.getMemberIdFromToken())
                .orElseThrow(MemberNotFoundException::new);
        MemberDto memberDto = MemberDto.of(member);
        return ApiResponse.success(ApiSuccessStatus.RETRIEVAL_SUCCESS,recommendationService.getRecommendations(memberDto));
    }
}
