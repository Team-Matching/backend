package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.member.dto.MemberSignUpRequest;
import com.cbnu.teammatching.member.dto.MemberSignUpResponse;
import com.cbnu.teammatching.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberSignUpResponse> signUp(@RequestBody MemberSignUpRequest memberDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signUp(memberDto));
    }
}
