package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Interest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InterestRequest {
    private List<InterestDto> interests;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InterestDto {
        private Long id;
        private String interest;
    }

    public static InterestDto of(Interest interest) {
        return new InterestDto(
                interest.getId(),
                interest.getInterest()
        );
    }
}