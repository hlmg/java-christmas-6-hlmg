package christmas.domain;

import christmas.domain.event.AppliedBenefits;
import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.EventManager;
import christmas.domain.event.PromotionEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;
import java.util.List;

public class ChristmasService {
    private final EventManager eventManager = EventManager.from(
            List.of(new ChristmasDDayEvent(),
                    new WeekdayEvent(),
                    new WeekendEvent(),
                    new SpecialEvent(),
                    new PromotionEvent()));

    //TODO: test 작성
    public String getEventBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= 20000) {
            return "산타";
        } else if (totalBenefitAmount >= 10000) {
            return "트리";
        } else if (totalBenefitAmount >= 5000) {
            return "별";
        }
        return "없음";
    }

    public AppliedBenefits plan(VisitDate visitDate, Order order) {
        return eventManager.getBenefits(visitDate, order);
    }
}
