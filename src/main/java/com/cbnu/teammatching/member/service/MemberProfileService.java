package com.cbnu.teammatching.member.service;

import com.cbnu.teammatching.exception.member.MemberNotFoundException;
import com.cbnu.teammatching.member.auth.JwtUtil;
import com.cbnu.teammatching.member.domain.Career;
import com.cbnu.teammatching.member.domain.Member;
import com.cbnu.teammatching.member.dto.CareerDto;
import com.cbnu.teammatching.member.repository.CareerRepository;
import com.cbnu.teammatching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberProfileService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final CareerRepository careerRepository;

    public List<CareerDto> getCareer(String token) {
        Long memberId = jwtUtil.getMemberId(token);
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        List<Career> careers = member.getCareers();
        return careers.stream()
                .map(career -> new CareerDto(
                        career.getId(),
                        career.getCompany(),
                        career.getRole(),
                        career.getStartDate(),
                        career.getEndDate(),
                        career.getDescription()
                )).collect(Collectors.toList());
    }

    public CareerDto saveCareer(String token, CareerDto careerDto) {
        Long memberId = jwtUtil.getMemberId(token);
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        if (careerDto.getCareerId() == null) {
            Career newCareer = Career.createCareer(member, careerDto);
            careerRepository.save(newCareer);
            return CareerDto.of(newCareer);
        } else {
            Career career = member.getCareers().stream().filter(i -> Objects.equals(i.getId(), careerDto.getCareerId())).findFirst().orElseThrow(MemberNotFoundException::new);
            career.updateCareer(careerDto);
            return careerDto;
        }
    }
}
