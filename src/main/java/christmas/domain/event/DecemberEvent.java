package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.LocalDate;

public abstract class DecemberEvent implements Event {
    private static final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private static final LocalDate endDate = LocalDate.of(2023, 12, 31);


    @Override
    public boolean isSatisfiedBy(VisitDate visitDate, Order order) {
        return isInEventPeriod(visitDate) && isSatisfiedCondition(visitDate, order);
    }

    private boolean isInEventPeriod(VisitDate visitDate) {
        return visitDate.isIn(startDate, endDate);
    }

    protected abstract boolean isSatisfiedCondition(VisitDate visitDate, Order order);
}
