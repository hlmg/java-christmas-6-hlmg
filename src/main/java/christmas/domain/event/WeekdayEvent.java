package christmas.domain.event;

import christmas.domain.Plan;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekdayEvent extends DecemberEvent {

    // 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    @Override
    public Benefit getBenefitFrom(Plan plan) {
        int discountPerUnit = 2023;
        LocalDate visitDate = plan.getDate();
        if (visitDate.getDayOfWeek() != DayOfWeek.FRIDAY && visitDate.getDayOfWeek() != DayOfWeek.SATURDAY) {
            int dessertMenuQuantity = plan.getDessertMenuQuantity();
            return Benefit.from(discountPerUnit * dessertMenuQuantity, List.of());
        }
        return Benefit.from(0, List.of());
    }

    @Override
    public String getName() {
        return "평일 할인";
    }
}
