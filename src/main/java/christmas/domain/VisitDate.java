package christmas.domain;

import java.time.DateTimeException;
import java.time.LocalDate;

public class VisitDate {
    private final LocalDate date;

    private VisitDate(LocalDate date) {
        this.date = date;
    }

    public static VisitDate from(int dayOfMonth) {
        LocalDate date = dateOf(dayOfMonth);
        return new VisitDate(date);
    }

    private static LocalDate dateOf(int dayOfMonth) {
        try {
            return LocalDate.of(2023, 12, dayOfMonth);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public boolean isIn(LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
