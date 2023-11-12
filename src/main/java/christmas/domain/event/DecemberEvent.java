package christmas.domain.event;

import christmas.domain.Plan;
import java.time.LocalDate;

public abstract class DecemberEvent implements Event {

    // 이벤트 기간: 2023.12.1 ~ 2023.12.31
    @Override
    public boolean isSatisfiedBy(Plan plan) {
        return isInEventPeriod(plan) && isSatisfiedCondition(plan);
    }

    private static boolean isInEventPeriod(Plan plan) {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        return plan.isIn(startDate, endDate);
    }

    protected abstract boolean isSatisfiedCondition(Plan plan);
}
