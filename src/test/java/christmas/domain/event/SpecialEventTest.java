package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.Plan;
import christmas.domain.VisitDate;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class SpecialEventTest {

    // 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
    // 일요일 or 25일
    @ParameterizedTest
    @CsvSource(textBlock = """
            양송이수프, 3, 1000
            양송이수프, 4, 0
            양송이수프, 5, 0
            양송이수프, 6, 0
            양송이수프, 7, 0
            양송이수프, 8, 0
            양송이수프, 9, 0
            양송이수프, 25, 1000
            """)
    void 증정_이벤트_혜택_테스트(String menuName, int dayOfMonth, int expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderItem.from(menuName, 1)));
        Plan plan = Plan.from(visitDate, order);

        Benefit actual = new SpecialEvent().getBenefitFrom(plan);

        assertThat(actual).isEqualTo(Benefit.from(expected, List.of()));
    }
}
