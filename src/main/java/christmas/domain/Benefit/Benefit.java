package christmas.domain.Benefit;

import christmas.domain.Menu;
import java.util.Optional;

public interface Benefit {
    String getEventName();

    int getDiscountAmount();

    int getBenefitAmount();

    Optional<Menu> getPromotionMenu();
}
