package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.util.List;

public class EventManager {
    public static final int MIN_ORDER_AMOUNT = 10000;
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

// 프로모션 이벤트
// 할인 이벤트

// 할인 이벤트의 경우 만족하면

// 할인 이벤트
// getEventname, getdiscountAmount
// 증정 이벤트
// getEventName, getPromotionMenu


