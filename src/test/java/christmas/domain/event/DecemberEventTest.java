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
public class DecemberEventTest {

    // 이벤트 기간: 2023.12.1 ~ 2023.12.31
    @ParameterizedTest
    @CsvSource(textBlock = """
            1, true
            25, true
            31, true
            """)
    void 십이월_이벤트_조건_테스트(int dayOfMonth, boolean expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderItem.from("타파스", 2)));
        Plan plan = Plan.from(visitDate, order);
        DecemberEvent decemberEvent = new PromotionEvent();

        boolean actual = decemberEvent.isSatisfiedBy(plan);

        assertThat(actual).isEqualTo(expected);
    }
}