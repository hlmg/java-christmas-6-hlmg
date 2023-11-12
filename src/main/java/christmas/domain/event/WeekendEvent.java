package christmas.domain.event;

import christmas.domain.Plan;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekendEvent extends DecemberEvent {

    @Override
    protected boolean isSatisfiedCondition(Plan plan) {
        LocalDate visitDate = plan.getDate();
        return visitDate.getDayOfWeek() == DayOfWeek.FRIDAY || visitDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    // 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
    @Override
    public Benefit getBenefitFrom(Plan plan) {
        int discountPerUnit = 2023;
        int mainMenuQuantity = plan.getMainMenuQuantity();
        return Benefit.from(discountPerUnit * mainMenuQuantity, List.of());
    }

    @Override
    public String getName() {
        return "주말 할인";
    }
}
