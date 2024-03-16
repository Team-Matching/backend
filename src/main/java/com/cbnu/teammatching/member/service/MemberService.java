package com.cbnu.teammatching.member.service;

import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.dto.MemberRegistrationDto;
import com.cbnu.teammatching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long register(MemberRegistrationDto dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member member = Member.createMember(dto);
        memberRepository.save(member);

        return member.getId();
    }

}
