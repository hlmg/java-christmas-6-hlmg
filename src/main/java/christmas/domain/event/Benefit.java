package christmas.domain.event;

import christmas.domain.Menu;
import java.util.List;
import java.util.Objects;

public class Benefit {
    private final int discountAmount;
    private final List<Menu> promotionMenus;

    private Benefit(int discountAmount, List<Menu> promotionMenus) {
        this.discountAmount = discountAmount;
        this.promotionMenus = promotionMenus;
    }

    public static Benefit from(int discountAmount, List<Menu> promotionMenu) {
        return new Benefit(discountAmount, promotionMenu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Benefit benefit)) {
            return false;
        }
        return discountAmount == benefit.discountAmount && Objects.equals(promotionMenus, benefit.promotionMenus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountAmount, promotionMenus);
    }
}
