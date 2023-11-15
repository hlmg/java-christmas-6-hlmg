package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DiscountBenefit;
import java.time.DayOfWeek;
import java.time.LocalDate;

public final class WeekdayEvent extends DecemberEvent {
    private static final String EVENT_NAME = "평일 할인";
    private static final int DISCOUNT_PER_UNIT = 2023;

    @Override
    protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
        return isWeekday(visitDate.getDate()) && hasDessert(order);
    }

    private boolean isWeekday(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    private boolean hasDessert(Order order) {
        return order.getDessertMenuQuantity() != 0;
    }

    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        int dessertMenuQuantity = order.getDessertMenuQuantity();
        return DiscountBenefit.from(EVENT_NAME, dessertMenuQuantity * DISCOUNT_PER_UNIT);
    }
}
