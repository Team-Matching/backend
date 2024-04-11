package com.cbnu.teammatching.member.service;

import com.cbnu.teammatching.exception.member.DuplicatedMemberFieldException;
import com.cbnu.teammatching.exception.member.InvalidIdOrPasswordException;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.dto.MemberSignInRequest;
import com.cbnu.teammatching.member.dto.MemberSignInResponse;
import com.cbnu.teammatching.member.dto.MemberSignUpRequest;
import com.cbnu.teammatching.member.dto.MemberSignUpResponse;
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

    public MemberSignUpResponse signUp(MemberSignUpRequest signUpRequest) {
        signUpRequest.encodePassword(passwordEncoder.encode(signUpRequest.getPassword()));

        if (memberRepository.existsByEmail(signUpRequest.getEmail()) ||
            memberRepository.existsByUsername(signUpRequest.getUsername())||
            memberRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            throw new DuplicatedMemberFieldException();
        }

        Member member = Member.createMember(signUpRequest);
        memberRepository.save(member);
        return MemberSignUpResponse.of(member);
    }

    public MemberSignInResponse signIn(MemberSignInRequest signInRequest) {
        Member member = memberRepository.findByUsername(signInRequest.getUsername()).orElseThrow(InvalidIdOrPasswordException::new);

        if (!passwordEncoder.matches(signInRequest.getPassword(), member.getPassword())){
            throw new InvalidIdOrPasswordException();
        }

        // TODO: 로그인 성공, JWT 발급, 응답 DTO 생성

        return MemberSignInResponse.of(member);
    }
}
