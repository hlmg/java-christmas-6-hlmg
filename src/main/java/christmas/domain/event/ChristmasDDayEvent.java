package christmas.domain.event;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import java.time.LocalDate;
import java.util.List;

public class ChristmasDDayEvent implements Event {

    /*
    - 크리스마스 디데이 할인
    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
     */
    @Override
    public boolean isSatisfiedBy(VisitDate visitDate, Order order) {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);

        return visitDate.isIn(startDate, endDate);
    }

    /*
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - 총주문 금액에서 해당 금액만큼 할인
      (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
     */
    @Override
    public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        int days = visitDate.getDaysFrom(startDate);
        int dailyDiscountIncrement = 100;
        int baseDiscountAmount = 1000;
        int totalDiscountAmount = baseDiscountAmount + (dailyDiscountIncrement * days);

        return Benefit.from(totalDiscountAmount, List.of());
    }

    @Override
    public String getName() {
        return "크리스마스 디데이 할인";
    }
}
