package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DiscountBenefit;
import java.time.DayOfWeek;
import java.time.LocalDate;

public final class SpecialEvent extends DecemberEvent {
    public static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    public static final String EVENT_NAME = "특별 할인";
    public static final int DISCOUNT_AMOUNT = 1000;

    @Override
    protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
        return isSpecialDay(visitDate.getDate());
    }

    private boolean isSpecialDay(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.isEqual(CHRISTMAS);
    }

    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        return DiscountBenefit.from(EVENT_NAME, DISCOUNT_AMOUNT);
    }
}
