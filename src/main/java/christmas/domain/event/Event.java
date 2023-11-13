package christmas.domain.event;

import christmas.domain.Benefit.Benefit;
import christmas.domain.Order;
import christmas.domain.VisitDate;

public interface Event {

    boolean isSatisfiedBy(VisitDate visitDate, Order order);

    Benefit getBenefitFrom(VisitDate visitDate, Order order);
}
