package com.cbnu.teammatching.member.service;

import com.cbnu.teammatching.exception.member.DuplicatedMemberFieldException;
import com.cbnu.teammatching.exception.member.InvalidEmailOrPasswordException;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.dto.*;
import com.cbnu.teammatching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    public MemberSignUpResponse signUp(MemberSignUpRequest signUpRequest) {
        signUpRequest.encodePassword(passwordEncoder.encode(signUpRequest.getPassword()));

        if (memberRepository.existsByEmail(signUpRequest.getEmail()) ||
            memberRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            throw new DuplicatedMemberFieldException();
        }

        Member member = Member.createMember(signUpRequest);
        memberRepository.save(member);
        return MemberSignUpResponse.of(member);
    }

    public MemberSignInResponse signIn(MemberSignInRequest signInRequest) {
        Member member = memberRepository.findByEmail(signInRequest.getEmail()).orElseThrow(InvalidEmailOrPasswordException::new);

        if (!passwordEncoder.matches(signInRequest.getPassword(), member.getPassword())){
            throw new InvalidEmailOrPasswordException();
        }

        CustomUserInfoDto info = modelMapper.map(member, CustomUserInfoDto.class);

        String accessToken = jwtUtil.createAccessToken(info);
        return new MemberSignInResponse(member.getEmail(), accessToken);

    }
}
