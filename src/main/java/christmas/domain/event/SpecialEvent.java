package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SpecialEvent extends DecemberEvent {

    @Override
    protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
        LocalDate date = visitDate.getDate();
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.isEqual(LocalDate.of(2023, 12, 25));
    }

    // 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
    // 일요일 or 25일
    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        return Benefit.from("특별 할인", 1000, List.of());
    }
}
