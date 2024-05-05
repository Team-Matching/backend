package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.member.dto.CareerDto;
import com.cbnu.teammatching.member.service.MemberProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/api/member/profile")
@RequiredArgsConstructor
public class MemberProfileController {

    private final MemberProfileService profileService;

    @GetMapping("career")
    public ResponseEntity<ApiResponse<List<CareerDto>>> getCareer(@RequestHeader(name = "Authorization") String token) {
        List<CareerDto> careers = profileService.getCareer(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, careers);
    }

    @PostMapping("career")
    public ResponseEntity<ApiResponse<CareerDto>> saveCareer(@RequestHeader(name = "Authorization") String token, @RequestBody CareerDto careerDto) {
        CareerDto careerResponse = profileService.saveCareer(token, careerDto);
        return ApiResponse.success(PROFILE_CAREER_SAVE, careerResponse);
    }
}
