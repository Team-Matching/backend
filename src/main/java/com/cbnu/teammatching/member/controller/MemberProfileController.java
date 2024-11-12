package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.member.dto.*;
import com.cbnu.teammatching.member.service.MemberProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/api/members/profiles")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "프로필 API", description = "회원 프로필 관련 API")
public class MemberProfileController {

    private final MemberProfileService profileService;

    @PostMapping("/careers")
    public ResponseEntity<ApiResponse<CareerDto>> saveCareer(@RequestBody CareerDto careerDto) {
        CareerDto careerResponse = profileService.saveCareer(careerDto);
        return ApiResponse.success(PROFILE_SAVE, careerResponse);
    }

    @GetMapping("/careers")
    public ResponseEntity<ApiResponse<List<CareerDto>>> getCareer() {
        List<CareerDto> careers = profileService.getCareer();
        return ApiResponse.success(RETRIEVAL_SUCCESS, careers);
    }

    @PostMapping("/certifications")
    public ResponseEntity<ApiResponse<CertificationDto>> saveCertification(@RequestBody CertificationDto certificationDto) {
        CertificationDto certificationResponse = profileService.saveCertification(certificationDto);
        return ApiResponse.success(PROFILE_SAVE, certificationResponse);
    }

    @GetMapping("/certifications")
    public ResponseEntity<ApiResponse<List<CertificationDto>>> getCertification() {
        List<CertificationDto> certifications = profileService.getCertification();
        return ApiResponse.success(RETRIEVAL_SUCCESS, certifications);
    }

    @PostMapping("/educations")
    public ResponseEntity<ApiResponse<EducationDto>> saveEducation(@RequestBody EducationDto educationDto) {
        EducationDto educationResponse = profileService.saveEducation(educationDto);
        return ApiResponse.success(PROFILE_SAVE, educationResponse);
    }

    @GetMapping("/educations")
    public ResponseEntity<ApiResponse<List<EducationDto>>> getEducation() {
        List<EducationDto> educations = profileService.getEducation();
        return ApiResponse.success(RETRIEVAL_SUCCESS, educations);
    }

    @PostMapping("/skills")
    public ResponseEntity<ApiResponse<List<SkillRequest.SkillDto>>> saveSkill(@RequestBody SkillRequest skillRequest) {
        List<SkillRequest.SkillDto> skills = profileService.saveSkills(skillRequest.getSkills());
        return ApiResponse.success(PROFILE_SAVE, skills);
    }

    @GetMapping("/skills")
    public ResponseEntity<ApiResponse<List<SkillRequest.SkillDto>>> getSkill() {
        List<SkillRequest.SkillDto> skills = profileService.getSkill();
        return ApiResponse.success(RETRIEVAL_SUCCESS, skills);
    }

    @PostMapping("/interests")
    public ResponseEntity<ApiResponse<List<InterestRequest.InterestDto>>> saveInterest(@RequestBody InterestRequest interestRequest) {
        List<InterestRequest.InterestDto> interests = profileService.saveInterests(interestRequest.getInterests());
        return ApiResponse.success(PROFILE_SAVE, interests);
    }

    @GetMapping("/interests")
    public ResponseEntity<ApiResponse<List<InterestRequest.InterestDto>>> getInterest() {
        List<InterestRequest.InterestDto> interests = profileService.getInterest();
        return ApiResponse.success(RETRIEVAL_SUCCESS, interests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberProfileDto>> getProfileDetails(@PathVariable Long id) {
        MemberProfileDto profile = profileService.getProfile(id);
        return ApiResponse.success(RETRIEVAL_SUCCESS, profile);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberProfileDto>>> getProfiles() {
        List<MemberProfileDto> profiles = profileService.getProfiles();
        return ApiResponse.success(RETRIEVAL_SUCCESS, profiles);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<ApiResponse<MyProfileResponse>> getMyProfile() {
        MyProfileResponse myProfile = profileService.getMyProfile();
        return ApiResponse.success(RETRIEVAL_SUCCESS, myProfile);
    }
}
