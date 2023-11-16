package christmas.domain.benefit;

import christmas.domain.Menu;
import java.util.Objects;
import java.util.Optional;

public final class PromotionBenefit implements Benefit {
    private final String eventName;
    private final Menu promotionMenu;

    private PromotionBenefit(String eventName, Menu promotionMenu) {
        this.eventName = eventName;
        this.promotionMenu = promotionMenu;
    }

    public static Benefit from(String eventName, Menu menu) {
        return new PromotionBenefit(eventName, menu);
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public int getDiscountAmount() {
        return 0;
    }

    @Override
    public int getBenefitAmount() {
        return promotionMenu.getPrice();
    }

    @Override
    public Optional<Menu> getPromotionMenu() {
        return Optional.of(promotionMenu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PromotionBenefit that)) {
            return false;
        }
        return Objects.equals(eventName, that.eventName) && promotionMenu == that.promotionMenu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, promotionMenu);
    }
}
