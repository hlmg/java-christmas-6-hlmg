package christmas.domain.event;

import christmas.domain.Plan;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SpecialEvent extends DecemberEvent {

    @Override
    protected boolean isSatisfiedCondition(Plan plan) {
        LocalDate visitDate = plan.getDate();
        return visitDate.getDayOfWeek() == DayOfWeek.SUNDAY || visitDate.isEqual(LocalDate.of(2023, 12, 25));
    }

    // 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
    // 일요일 or 25일
    @Override
    public Benefit getBenefitFrom(Plan plan) {
        return Benefit.from(1000, List.of());
    }

    @Override
    public String getName() {
        return "특별 할인";
    }
}
