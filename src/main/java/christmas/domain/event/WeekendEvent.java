package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DiscountBenefit;
import java.time.DayOfWeek;
import java.time.LocalDate;

public final class WeekendEvent extends DecemberEvent {
    private static final String EVENT_NAME = "주말 할인";
    private static final int DISCOUNT_PER_UNIT = 2023;

    @Override
    protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
        return isWeekend(visitDate.getDate()) && hasMainMenu(order);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    private boolean hasMainMenu(Order order) {
        return order.getMainMenuQuantity() != 0;
    }

    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        int mainMenuQuantity = order.getMainMenuQuantity();
        return DiscountBenefit.from(EVENT_NAME, mainMenuQuantity * DISCOUNT_PER_UNIT);
    }
}
