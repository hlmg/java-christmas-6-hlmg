package christmas.domain.event;

import christmas.domain.Menu;
import christmas.domain.Plan;
import java.util.List;

public class PromotionEvent extends DecemberEvent {

    // 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
    @Override
    public Benefit getBenefitFrom(Plan plan) {
        if (plan.getTotalOrderAmount() >= 120_000) {
            return Benefit.from(0, List.of(Menu.CHAMPAGNE));
        }
        return Benefit.from(0, List.of());
    }

    @Override
    public String getName() {
        return "증정 이벤트";
    }
}
