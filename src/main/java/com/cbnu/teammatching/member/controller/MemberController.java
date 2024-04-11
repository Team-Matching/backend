package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.member.dto.MemberSignInRequest;
import com.cbnu.teammatching.member.dto.MemberSignInResponse;
import com.cbnu.teammatching.member.dto.MemberSignUpRequest;
import com.cbnu.teammatching.member.dto.MemberSignUpResponse;
import com.cbnu.teammatching.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<MemberSignInResponse>> signIn(@Validated @RequestBody MemberSignInRequest signInRequest) {
        return ApiResponse.success(SIGNIN_SUCCESS, memberService.signIn(signInRequest));
    }
}
