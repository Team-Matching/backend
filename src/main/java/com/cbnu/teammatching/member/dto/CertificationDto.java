package com.cbnu.teammatching.member.dto;

import com.cbnu.teammatching.member.domain.Certification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDto {

    private Long certificationId;

    private String certificationName;

    private String issuer;

    private LocalDate dateObtained;

    public static CertificationDto of(Certification certification) {
        return new CertificationDto(
                certification.getId(),
                certification.getCertificationName(),
                certification.getIssuer(),
                certification.getDateObtained()
        );
    }
}
