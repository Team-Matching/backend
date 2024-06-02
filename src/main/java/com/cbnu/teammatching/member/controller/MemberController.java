package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.common.response.ApiResponse;
import com.cbnu.teammatching.member.dto.MemberSignInRequest;
import com.cbnu.teammatching.member.dto.MemberSignInResponse;
import com.cbnu.teammatching.member.dto.MemberSignUpRequest;
import com.cbnu.teammatching.member.dto.MemberSignUpResponse;
import com.cbnu.teammatching.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cbnu.teammatching.common.response.ApiSuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "회원 API", description = "회원 가입 및 로그인 관련 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원 가입", description = "회원 가입을 수행합니다.")
    public ResponseEntity<ApiResponse<MemberSignUpResponse>> signUp(@Validated @RequestBody MemberSignUpRequest memberDto) {
        return ApiResponse.success(SIGNUP_SUCCESS, memberService.signUp(memberDto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<MemberSignInResponse>> signIn(@Validated @RequestBody MemberSignInRequest signInRequest) {
        MemberSignInResponse member = memberService.signIn(signInRequest);
        return ApiResponse.success(SIGNIN_SUCCESS, member);
    }

}
