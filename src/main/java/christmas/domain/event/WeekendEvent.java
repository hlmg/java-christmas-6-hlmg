package christmas.domain.event;

import christmas.domain.Benefit.Benefit;
import christmas.domain.Benefit.DiscountBenefit;
import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendEvent extends DecemberEvent {
    public static final String EVENT_NAME = "주말 할인";
    public static final int DISCOUNT_PER_UNIT = 2023;

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

    // 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        int mainMenuQuantity = order.getMainMenuQuantity();
        return DiscountBenefit.from(EVENT_NAME, mainMenuQuantity * DISCOUNT_PER_UNIT);
    }
}
