package com.cbnu.teammatching.member.domain;

import com.cbnu.teammatching.member.dto.CareerDto;
import com.cbnu.teammatching.member.dto.CertificationDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String certificationName;
    private String issuer;

    private LocalDate dateObtained;

    public static Certification createCertification(Member member, CertificationDto dto) {
        Certification certification = new Certification();
        certification.member = member;
        certification.certificationName = dto.getCertificationName();
        certification.issuer = dto.getIssuer();
        certification.dateObtained = dto.getDateObtained();
        member.getCertifications().add(certification);
        return certification;
    }

    public void updateCertification(CertificationDto update) {
        this.certificationName = update.getCertificationName();
        this.issuer = update.getIssuer();
        this.dateObtained = update.getDateObtained();
    }
}
