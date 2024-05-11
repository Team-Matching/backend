package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.dto.CareerDto;
import com.cbnu.teammatching.member.dto.CertificationDto;
import com.cbnu.teammatching.member.dto.EducationDto;
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

    @PostMapping("/career")
    public ResponseEntity<ApiResponse<CareerDto>> saveCareer(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody CareerDto careerDto) {
        String token = JwtUtil.extractJwtToken(accessToken);
        CareerDto careerResponse = profileService.saveCareer(token, careerDto);
        return ApiResponse.success(PROFILE_SAVE, careerResponse);
    }

    @GetMapping("/career")
    public ResponseEntity<ApiResponse<List<CareerDto>>> getCareer(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<CareerDto> careers = profileService.getCareer(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, careers);
    }

    @PostMapping("/certification")
    public ResponseEntity<ApiResponse<CertificationDto>> saveCertification(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody CertificationDto certificationDto) {
        String token = JwtUtil.extractJwtToken(accessToken);
        CertificationDto certificationResponse = profileService.saveCertification(token, certificationDto);
        return ApiResponse.success(PROFILE_SAVE, certificationResponse);
    }

    @GetMapping("/certification")
    public ResponseEntity<ApiResponse<List<CertificationDto>>> getCertification(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<CertificationDto> certifications = profileService.getCertification(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, certifications);
    }

    @PostMapping("/education")
    public ResponseEntity<ApiResponse<EducationDto>> saveEducation(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody EducationDto educationDto) {
        String token = JwtUtil.extractJwtToken(accessToken);
        EducationDto educationResponse = profileService.saveEducation(token, educationDto);
        return ApiResponse.success(PROFILE_SAVE, educationResponse);
    }

    @GetMapping("/education")
    public ResponseEntity<ApiResponse<List<EducationDto>>> getEducation(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<EducationDto> educations = profileService.getEducation(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, educations);
    }
}
