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
class WeekdayEventTest {

    // 평일: (일요일 ~ 목요일) + 디저트 메뉴 하나 이상
    @ParameterizedTest
    @CsvSource(textBlock = """
            1, 초코케이크, false
            2, 초코케이크, false
            3, 초코케이크, true
            4, 초코케이크, true
            5, 초코케이크, true
            6, 초코케이크, true
            7, 초코케이크, true
            7, 티본스테이크, false
            """)
    void 평일_이벤트_조건_테스트(int dayOfMonth, String menuName, boolean expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderMenu.from(menuName, 1)));
        WeekdayEvent weekdayEvent = new WeekdayEvent();
        boolean actual = weekdayEvent.isSatisfiedCondition(visitDate, order);

        assertThat(actual).isEqualTo(expected);
    }

    // 디저트 메뉴를 메뉴 1개당 2,023원 할인
    @Test
    void 평일_이벤트_혜택_테스트() {
        VisitDate visitDate = VisitDate.from(1);
        Order order = Order.from(List.of(
                OrderMenu.from("초코케이크", 1),
                OrderMenu.from("아이스크림", 2),
                OrderMenu.from("티본스테이크", 10))
        );

        Benefit actual = new WeekdayEvent().getBenefitFrom(visitDate, order);

        assertThat(actual).isEqualTo(Benefit.from("평일 할인", 2023 * 3, List.of()));
    }
}
