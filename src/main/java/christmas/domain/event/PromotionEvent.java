package christmas.domain.event;

import christmas.domain.Benefit.Benefit;
import christmas.domain.Benefit.PromotionBenefit;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.VisitDate;

public class PromotionEvent extends DecemberEvent {
    public static final String EVENT_NAME = "증정 이벤트";
    public static final int MIN_ORDER_AMOUNT = 120_000;

    @Override
    protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
        return order.getTotalOrderAmount() >= MIN_ORDER_AMOUNT;
    }

    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        return PromotionBenefit.from(EVENT_NAME, Menu.CHAMPAGNE);
    }
}
