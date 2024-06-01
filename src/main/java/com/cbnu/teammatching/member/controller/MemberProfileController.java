package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.dto.*;
import com.cbnu.teammatching.member.service.MemberProfileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/api/members/profile")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@Slf4j
public class MemberProfileController {

    private final MemberProfileService profileService;

    @PostMapping("/careers")
    public ResponseEntity<ApiResponse<CareerDto>> saveCareer(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody CareerDto careerDto) {
        String token = JwtUtil.extractJwtToken(accessToken);
        CareerDto careerResponse = profileService.saveCareer(token, careerDto);
        return ApiResponse.success(PROFILE_SAVE, careerResponse);
    }

    @GetMapping("/careers")
    public ResponseEntity<ApiResponse<List<CareerDto>>> getCareer(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<CareerDto> careers = profileService.getCareer(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, careers);
    }

    @PostMapping("/certifications")
    public ResponseEntity<ApiResponse<CertificationDto>> saveCertification(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody CertificationDto certificationDto) {
        String token = JwtUtil.extractJwtToken(accessToken);
        CertificationDto certificationResponse = profileService.saveCertification(token, certificationDto);
        return ApiResponse.success(PROFILE_SAVE, certificationResponse);
    }

    @GetMapping("/certifications")
    public ResponseEntity<ApiResponse<List<CertificationDto>>> getCertification(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<CertificationDto> certifications = profileService.getCertification(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, certifications);
    }

    @PostMapping("/educations")
    public ResponseEntity<ApiResponse<EducationDto>> saveEducation(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody EducationDto educationDto) {
        String token = JwtUtil.extractJwtToken(accessToken);
        EducationDto educationResponse = profileService.saveEducation(token, educationDto);
        return ApiResponse.success(PROFILE_SAVE, educationResponse);
    }

    @GetMapping("/educations")
    public ResponseEntity<ApiResponse<List<EducationDto>>> getEducation(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<EducationDto> educations = profileService.getEducation(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, educations);
    }

    @PostMapping("/skills")
    public ResponseEntity<ApiResponse<List<SkillRequest.SkillDto>>> saveSkill(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody SkillRequest skillRequest
    ) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<SkillRequest.SkillDto> skills = profileService.saveSkills(token, skillRequest.getSkills());
        return ApiResponse.success(PROFILE_SAVE, skills);
    }

    @GetMapping("/skills")
    public ResponseEntity<ApiResponse<List<SkillRequest.SkillDto>>> getSkill(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<SkillRequest.SkillDto> skills = profileService.getSkill(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, skills);
    }

    @PostMapping("/interests")
    public ResponseEntity<ApiResponse<List<InterestRequest.InterestDto>>> saveInterest(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody InterestRequest interestRequest
    ) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<InterestRequest.InterestDto> interests = profileService.saveInterests(token, interestRequest.getInterests());
        return ApiResponse.success(PROFILE_SAVE, interests);
    }

    @GetMapping("/interests")
    public ResponseEntity<ApiResponse<List<InterestRequest.InterestDto>>> getInterest(@RequestHeader(name = "Authorization") String accessToken) {
        String token = JwtUtil.extractJwtToken(accessToken);
        List<InterestRequest.InterestDto> interests = profileService.getInterest(token);
        return ApiResponse.success(RETRIEVAL_SUCCESS, interests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberProfileDto>> getProfile(@PathVariable Long id) {
        MemberProfileDto profile = profileService.getProfile(id);
        return ApiResponse.success(RETRIEVAL_SUCCESS, profile);
    }
}
