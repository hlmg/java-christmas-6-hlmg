package christmas.domain;

import static christmas.common.ExceptionMessages.INVALID_DATE;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class VisitDate {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;

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
            return LocalDate.of(YEAR, MONTH, dayOfMonth);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public boolean isIn(LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public int getDaysFrom(LocalDate baseDate) {
        return (int) ChronoUnit.DAYS.between(baseDate, date);
    }

    public LocalDate getDate() {
        return date;
    }
}
