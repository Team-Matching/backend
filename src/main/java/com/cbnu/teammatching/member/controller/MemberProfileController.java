package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.dto.CareerDto;
import com.cbnu.teammatching.member.service.MemberProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/api/member/profile")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@Slf4j
public class MemberProfileController {

    private final MemberProfileService profileService;

    @PostMapping("career")
    public ResponseEntity<ApiResponse<CareerDto>> saveCareer(@RequestHeader(name = "Authorization") String accessToken, @RequestBody CareerDto careerDto) {
        String token = JwtUtil.extractJwtToken(accessToken);
        CareerDto careerResponse = profileService.saveCareer(token, careerDto);
        return ApiResponse.success(PROFILE_CAREER_SAVE, careerResponse);
    }

    @GetMapping("career")
    public ResponseEntity<ApiResponse<List<CareerDto>>> getCareer(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<CareerDto> careers = profileService.getCareer(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, careers);
    }


}
