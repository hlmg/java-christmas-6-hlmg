package christmas.domain.event;

import christmas.domain.Plan;

public interface Event {

    boolean isSatisfiedBy(Plan plan);
}
