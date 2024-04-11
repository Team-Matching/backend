package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.member.dto.MemberSignInRequest;
import com.cbnu.teammatching.member.dto.MemberSignInResponse;
import com.cbnu.teammatching.member.dto.MemberSignUpRequest;
import com.cbnu.teammatching.member.dto.MemberSignUpResponse;
import com.cbnu.teammatching.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<MemberSignUpResponse>> signUp(@Validated @RequestBody MemberSignUpRequest memberDto) {
        return ApiResponse.success(SIGNUP_SUCCESS,memberService.signUp(memberDto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<ApiResponse<String>> signIn(@Validated @RequestBody MemberSignInRequest signInRequest) {
        MemberSignInResponse member = memberService.signIn(signInRequest);
        return ApiResponse.success(SIGNIN_SUCCESS, member.getEmail(), member.getToken());
    }

    @PostMapping("/ex")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> ex() {
        return ResponseEntity.ok().body("인증 성공");
    }
}
