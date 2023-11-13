package christmas.domain.event;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.VisitDate;

public class PromotionEvent extends DecemberEvent {

    // 할인 전 총주문 금액이 12만 원 이상
    @Override
    protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
        return order.getTotalOrderAmount() >= 120_000;
    }

    // 샴페인 1개 증정
    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        return PromotionBenefit.from("증정 이벤트", Menu.CHAMPAGNE);
    }
}
