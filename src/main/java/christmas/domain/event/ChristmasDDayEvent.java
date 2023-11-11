package christmas.domain.event;

import christmas.domain.Plan;
import java.time.LocalDate;

public class ChristmasDDayEvent implements Event {

    /*
    - 크리스마스 디데이 할인
    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
     */
    @Override
    public boolean isSatisfiedBy(Plan plan) {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);

        return plan.isIn(startDate, endDate);
    }
}
