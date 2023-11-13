package christmas.domain.event;

import christmas.domain.Benefit.Benefit;
import christmas.domain.Benefit.DiscountBenefit;
import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayEvent extends DecemberEvent {
    public static final String EVENT_NAME = "평일 할인";
    public static final int DISCOUNT_PER_UNIT = 2023;

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

    // 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        int dessertMenuQuantity = order.getDessertMenuQuantity();
        return DiscountBenefit.from(EVENT_NAME, dessertMenuQuantity * DISCOUNT_PER_UNIT);
    }
}
