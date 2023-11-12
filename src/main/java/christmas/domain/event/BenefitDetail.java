package christmas.domain.event;

import christmas.domain.Menu;
import java.util.List;

public class BenefitDetail {
    private final Event event;
    private final Benefit benefit;

    private BenefitDetail(Event event, Benefit benefit) {
        this.event = event;
        this.benefit = benefit;
    }

    public static BenefitDetail from(Event event, Benefit benefit) {
        return new BenefitDetail(event, benefit);
    }

    public Event getEvent() {
        return event;
    }

    public List<Menu> getPromotionMenus() {
        return benefit.getPromotionMenus();
    }

    public int getDiscountAmount() {
        return benefit.getDiscountAmount();
    }
}
