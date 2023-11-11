package christmas.domain.event;

import christmas.domain.Plan;
import java.util.List;

public class EventManager {
    private final List<Event> events;

    private EventManager(List<Event> events) {
        this.events = List.copyOf(events);
    }

    public static EventManager from(List<Event> events) {
        return new EventManager(events);
    }

    public List<Event> getAppliedEvents(Plan plan) {
        return events.stream()
                .filter(event -> event.isSatisfiedBy(plan))
                .toList();
    }
}
