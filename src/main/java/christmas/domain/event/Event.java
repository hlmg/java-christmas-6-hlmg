package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.benefit.Benefit;

public interface Event {

    boolean isSatisfiedBy(VisitDate visitDate, Order order);

    Benefit getBenefitFrom(VisitDate visitDate, Order order);
}
