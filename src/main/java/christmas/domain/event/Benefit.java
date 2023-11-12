package christmas.domain.event;

import christmas.domain.Menu;
import java.util.List;
import java.util.Objects;

public class Benefit {
    private final String eventName;
    private final int discountAmount;
    private final List<Menu> promotionMenus;

    private Benefit(String eventName, int discountAmount, List<Menu> promotionMenus) {
        this.eventName = eventName;
        this.discountAmount = discountAmount;
        this.promotionMenus = promotionMenus;
    }

    public static Benefit from(String eventName, int discountAmount, List<Menu> promotionMenu) {
        return new Benefit(eventName, discountAmount, promotionMenu);
    }

    // TODO: 혜택 금액 테스트 작성
    public int getBenefitAmount() {
        int promotionAmount = promotionMenus.stream()
                .mapToInt(Menu::getPrice)
                .sum();
        return discountAmount + promotionAmount;
    }

    public String getEventName() {
        return eventName;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public List<Menu> getPromotionMenus() {
        return promotionMenus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Benefit benefit)) {
            return false;
        }
        return discountAmount == benefit.discountAmount && Objects.equals(eventName, benefit.eventName)
                && Objects.equals(promotionMenus, benefit.promotionMenus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, discountAmount, promotionMenus);
    }
}
