package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekendEvent extends DecemberEvent {

    @Override
    protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
        LocalDate date = visitDate.getDate();
        int mainMenuQuantity = order.getMainMenuQuantity();
        return (date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY)
                && mainMenuQuantity != 0;
    }

    // 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        int discountPerUnit = 2023;
        int mainMenuQuantity = order.getMainMenuQuantity();
        return Benefit.from("주말 할인", discountPerUnit * mainMenuQuantity, List.of());
    }
}
