package com.cbnu.teammatching.member.service;

import com.cbnu.teammatching.exception.member.DuplicatedMemberFieldException;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.dto.MemberSignUpRequest;
import com.cbnu.teammatching.member.dto.MemberSignUpResponse;
import com.cbnu.teammatching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponse signUp(MemberSignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        List<String> duplicateFields = new ArrayList<>();

        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            duplicateFields.add("이메일");
        }
        if (memberRepository.existsByUsername(signUpRequest.getUsername())) {
            duplicateFields.add("아이디");
        }
        if (memberRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            duplicateFields.add("전화번호");
        }

        if (!duplicateFields.isEmpty()) {
            throw new DuplicatedMemberFieldException(duplicateFields);
        }

        Member member = Member.createMember(signUpRequest);
        memberRepository.save(member);
        return MemberSignUpResponse.of(member);
    }

}
