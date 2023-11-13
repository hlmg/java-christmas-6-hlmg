package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayEvent extends DecemberEvent {

    @Override
    protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
        LocalDate date = visitDate.getDate();
        int dessertMenuQuantity = order.getDessertMenuQuantity();
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY
                && dessertMenuQuantity != 0;
    }

    // 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        int discountPerUnit = 2023;
        int dessertMenuQuantity = order.getDessertMenuQuantity();
        return DiscountBenefit.from("평일 할인", discountPerUnit * dessertMenuQuantity);
    }
}
