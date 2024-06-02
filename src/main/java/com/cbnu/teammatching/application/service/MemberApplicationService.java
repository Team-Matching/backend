package com.cbnu.teammatching.application.service;

import com.cbnu.teammatching.application.dto.MemberApplicationResponse;
import com.cbnu.teammatching.exception.member.MemberNotFoundException;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberApplicationService {

    private final MemberRepository memberRepository;

    public List<MemberApplicationResponse> getApplications() {
        Member member = memberRepository.findById(JwtUtil.getMemberIdFromToken())
                .orElseThrow(MemberNotFoundException::new);
        return member.getApplications().stream()
                .map(MemberApplicationResponse::of)
                .toList();
    }
}
