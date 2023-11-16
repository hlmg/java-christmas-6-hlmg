package christmas.domain;

import christmas.domain.benefit.Benefit;
import christmas.domain.event.Event;
import java.util.List;

public final class EventManager {
    private static final int MIN_ORDER_AMOUNT = 10_000;
    private final List<Event> events;

    private EventManager(List<Event> events) {
        this.events = List.copyOf(events);
    }

    public static EventManager from(List<Event> events) {
        return new EventManager(events);
    }

    public AppliedBenefits apply(VisitDate visitDate, Order order) {
        if (isLessThanMinOverAmount(order)) {
            return AppliedBenefits.from(List.of());
        }

        List<Benefit> benefits = getBenefits(visitDate, order);
        return AppliedBenefits.from(benefits);
    }

    private boolean isLessThanMinOverAmount(Order order) {
        return order.getTotalOrderAmount() < MIN_ORDER_AMOUNT;
    }

    private List<Benefit> getBenefits(VisitDate visitDate, Order order) {
        return events.stream()
                .filter(event -> event.isSatisfiedBy(visitDate, order))
                .map(event -> event.getBenefitFrom(visitDate, order))
                .toList();
    }
}
