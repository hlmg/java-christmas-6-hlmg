package christmas.domain;

import java.util.Arrays;

public enum EventBadge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NOTHING("없음", 0);

    private final String name;
    private final int amount;

    EventBadge(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public static EventBadge from(int totalBenefitAmount) {
        return Arrays.stream(EventBadge.values())
                .filter(eventBadge -> eventBadge.amount <= totalBenefitAmount)
                .findAny()
                .orElseThrow();
    }
}
