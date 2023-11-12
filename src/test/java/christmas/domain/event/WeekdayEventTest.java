package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.Plan;
import christmas.domain.VisitDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class WeekdayEventTest {

    // 평일: (일요일 ~ 목요일)
    @ParameterizedTest
    @CsvSource(textBlock = """
            1, false
            2, false
            3, true
            4, true
            5, true
            6, true
            7, true
            """)
    void 평일_이벤트_조건_테스트(int dayOfMonth, boolean expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderItem.from("타파스", 1)));
        Plan plan = Plan.from(visitDate, order);
        WeekdayEvent weekdayEvent = new WeekdayEvent();
        boolean actual = weekdayEvent.isSatisfiedCondition(plan);

        assertThat(actual).isEqualTo(expected);
    }

    // 디저트 메뉴를 메뉴 1개당 2,023원 할인
    @Test
    void 평일_이벤트_혜택_테스트() {
        VisitDate visitDate = VisitDate.from(1);
        Order order = Order.from(List.of(
                OrderItem.from("초코케이크", 1),
                OrderItem.from("아이스크림", 2),
                OrderItem.from("티본스테이크", 10))
        );
        Plan plan = Plan.from(visitDate, order);

        Benefit actual = new WeekdayEvent().getBenefitFrom(plan);

        assertThat(actual).isEqualTo(Benefit.from(2023 * 3, List.of()));
    }
}
