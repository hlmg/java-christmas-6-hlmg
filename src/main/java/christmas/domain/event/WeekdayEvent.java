package christmas.domain.event;

import christmas.domain.Plan;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekdayEvent extends DecemberEvent {

    @Override
    protected boolean isSatisfiedCondition(Plan plan) {
        LocalDate visitDate = plan.getDate();
        return visitDate.getDayOfWeek() != DayOfWeek.FRIDAY && visitDate.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    // 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    @Override
    public Benefit getBenefitFrom(Plan plan) {
        int discountPerUnit = 2023;
        int dessertMenuQuantity = plan.getDessertMenuQuantity();
        return Benefit.from(discountPerUnit * dessertMenuQuantity, List.of());
    }

    @Override
    public String getName() {
        return "평일 할인";
    }
}
