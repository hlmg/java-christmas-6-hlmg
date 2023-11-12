package christmas.domain.event;

import christmas.domain.Menu;
import christmas.domain.Plan;
import java.util.List;

public class PromotionEvent extends DecemberEvent {

    // 할인 전 총주문 금액이 12만 원 이상
    @Override
    protected boolean isSatisfiedCondition(Plan plan) {
        return plan.getTotalOrderAmount() >= 120_000;
    }

    // 샴페인 1개 증정
    @Override
    public Benefit getBenefitFrom(Plan plan) {
        return Benefit.from(0, List.of(Menu.CHAMPAGNE));
    }

    @Override
    public String getName() {
        return "증정 이벤트";
    }
}
