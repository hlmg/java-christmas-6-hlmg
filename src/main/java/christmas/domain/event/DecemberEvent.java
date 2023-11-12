package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.LocalDate;

public abstract class DecemberEvent implements Event {

    // 이벤트 기간: 2023.12.1 ~ 2023.12.31
    @Override
    public boolean isSatisfiedBy(VisitDate visitDate, Order order) {
        return isInEventPeriod(visitDate) && isSatisfiedCondition(visitDate, order);
    }

    private static boolean isInEventPeriod(VisitDate visitDate) {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        return visitDate.isIn(startDate, endDate);
    }

    protected abstract boolean isSatisfiedCondition(VisitDate visitDate, Order order);
}
