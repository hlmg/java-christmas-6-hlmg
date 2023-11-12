package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.util.List;

public class EventManager {
    private final List<Event> events;

    private EventManager(List<Event> events) {
        this.events = List.copyOf(events);
    }

    public static EventManager from(List<Event> events) {
        return new EventManager(events);
    }

    // private 으로 하거나 List<Event>를 가진 객체를 반환해도 될듯
    public List<Event> getAppliedEvents(VisitDate visitDate, Order order) {
        return events.stream()
                .filter(event -> event.isSatisfiedBy(visitDate, order))
                .toList();
    }

    public List<Benefit> getBenefits(VisitDate visitDate, Order order) {
        return getAppliedEvents(visitDate, order).stream()
                .map(event -> event.getBenefitFrom(visitDate, order))
                .toList();
    }
}
