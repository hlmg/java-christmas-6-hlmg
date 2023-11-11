package christmas.domain.event;

import christmas.domain.Plan;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekendEvent extends DecemberEvent {

    // 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
    @Override
    public Benefit getBenefitFrom(Plan plan) {
        int discountPerUnit = 2023;
        LocalDate visitDate = plan.getDate();
        if (visitDate.getDayOfWeek() == DayOfWeek.FRIDAY || visitDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            int mainMenuQuantity = plan.getMainMenuQuantity();
            return Benefit.from(discountPerUnit * mainMenuQuantity, List.of());
        }
        return Benefit.from(0, List.of());
    }
}
