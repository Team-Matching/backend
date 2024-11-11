package com.cbnu.teammatching.common.validator;

import java.time.LocalDate;

public class DateValidator {
    public static void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("시작일은 필수입니다.");
        }

        if (endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일이 종료일보다 늦을 수 없습니다.");
        }

        if (startDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("시작일이 현재 날짜보다 늦을 수 없습니다.");
        }
    }

    public static void validateDate(LocalDate dateObtained) {
        if (dateObtained == null) {
            throw new IllegalArgumentException("취득일은 필수입니다.");
        }

        if (dateObtained.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("취득일이 현재 날짜보다 늦을 수 없습니다.");
        }
    }
}
