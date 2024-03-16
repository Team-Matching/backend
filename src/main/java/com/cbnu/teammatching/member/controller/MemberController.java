package com.cbnu.teammatching.member.controller;

import com.cbnu.teammatching.member.dto.MemberRegistrationDto;
import com.cbnu.teammatching.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Long> registerMember(@RequestBody MemberRegistrationDto memberDto) {
        return ResponseEntity.ok(memberService.register(memberDto));
    }
}
