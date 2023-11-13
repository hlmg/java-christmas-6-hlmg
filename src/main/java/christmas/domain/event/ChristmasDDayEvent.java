package christmas.domain.event;

import christmas.domain.Benefit.Benefit;
import christmas.domain.Benefit.DiscountBenefit;
import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.LocalDate;

public class ChristmasDDayEvent implements Event {
    private static final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private static final LocalDate endDate = LocalDate.of(2023, 12, 25);
    public static final String EVENT_NAME = "크리스마스 디데이 할인";
    public static final int DAILY_DISCOUNT_INCREMENT = 100;
    public static final int BASE_DISCOUNT_AMOUNT = 1000;

    @Override
    public boolean isSatisfiedBy(VisitDate visitDate, Order order) {
        return visitDate.isIn(startDate, endDate);
    }

    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        int days = visitDate.getDaysFrom(startDate);
        int totalDiscountAmount = BASE_DISCOUNT_AMOUNT + (DAILY_DISCOUNT_INCREMENT * days);

        return DiscountBenefit.from(EVENT_NAME, totalDiscountAmount);
    }
}
