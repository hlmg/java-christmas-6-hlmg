package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.Plan;
import christmas.domain.VisitDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class SpecialEventTest {

    // 이벤트 달력에 별: 일요일 or 25일
    @ParameterizedTest
    @CsvSource(textBlock = """
            1, false
            2, false
            3, true
            4, false
            5, false
            6, false
            7, false
            25, true
            """)
    void 스페셜_이벤트_조건_테스트(int dayOfMonth, boolean expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderMenu.from("타파스", 1)));
        Plan plan = Plan.from(visitDate, order);
        SpecialEvent specialEvent = new SpecialEvent();
        boolean actual = specialEvent.isSatisfiedCondition(plan);

        assertThat(actual).isEqualTo(expected);
    }

    // 총주문 금액에서 1,000원 할인
    @Test
    void 스페셜_이벤트_혜택_테스트() {
        VisitDate visitDate = VisitDate.from(1);
        Order order = Order.from(List.of(OrderMenu.from("양송이수프", 1)));
        Plan plan = Plan.from(visitDate, order);

        Benefit actual = new SpecialEvent().getBenefitFrom(plan);

        assertThat(actual).isEqualTo(Benefit.from(1000, List.of()));
    }
}
