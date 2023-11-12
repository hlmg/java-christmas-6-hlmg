package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class WeekendEventTest {

    // 주말(금요일, 토요일) + 메인 메뉴 하나 이상
    @ParameterizedTest
    @CsvSource(textBlock = """
            1, 양송이수프, false
            1, 바비큐립, true
            2, 바비큐립, true
            3, 바비큐립, false
            4, 바비큐립, false
            5, 바비큐립, false
            6, 바비큐립, false
            7, 바비큐립, false
            """)
    void 주말_이벤트_조건_테스트(int dayOfMonth, String menuName, boolean expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderMenu.from(menuName, 1)));
        WeekendEvent weekendEvent = new WeekendEvent();
        boolean actual = weekendEvent.isSatisfiedCondition(visitDate, order);

        assertThat(actual).isEqualTo(expected);
    }

    // 메인 메뉴를 메뉴 1개당 2,023원 할인
    @Test
    void 주말_이벤트_혜택_테스트() {
        VisitDate visitDate = VisitDate.from(1);
        Order order = Order.from(List.of(
                OrderMenu.from("티본스테이크", 1),
                OrderMenu.from("바비큐립", 2),
                OrderMenu.from("초코케이크", 10))
        );

        Benefit actual = new WeekendEvent().getBenefitFrom(visitDate, order);

        assertThat(actual).isEqualTo(Benefit.from("주말 할인", 2023 * 3, List.of()));
    }
}
