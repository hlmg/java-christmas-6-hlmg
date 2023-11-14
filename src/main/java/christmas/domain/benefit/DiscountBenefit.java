package christmas.domain.benefit;

import christmas.domain.Menu;
import java.util.Objects;
import java.util.Optional;

public final class DiscountBenefit implements Benefit {
    private final String eventName;
    private final int discountAmount;

    private DiscountBenefit(String eventName, int discountAmount) {
        this.eventName = eventName;
        this.discountAmount = discountAmount;
    }

    public static DiscountBenefit from(String eventName, int discountAmount) {
        return new DiscountBenefit(eventName, discountAmount);
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public int getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public int getBenefitAmount() {
        return discountAmount;
    }

    @Override
    public Optional<Menu> getPromotionMenu() {
        return Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DiscountBenefit that)) {
            return false;
        }
        return discountAmount == that.discountAmount && Objects.equals(eventName, that.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, discountAmount);
    }
}
