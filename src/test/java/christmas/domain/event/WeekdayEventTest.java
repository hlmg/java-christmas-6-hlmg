package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.Plan;
import christmas.domain.VisitDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class WeekdayEventTest {

    // TODO: 테스트 나누기, 평일 확인하는 테스트 and 디저트 메뉴 확인하는 테스트
    // 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
    @ParameterizedTest
    @MethodSource()
    void 평일_이벤트_혜택_테스트(int dayOfMonth, List<OrderItem> orderItems, int expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(orderItems);
        Plan plan = Plan.from(visitDate, order);

        Benefit actual = new WeekdayEvent().getBenefitFrom(plan);

        assertThat(actual).isEqualTo(Benefit.from(expected, List.of()));
    }

    public static Stream<Arguments> 평일_이벤트_혜택_테스트() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                OrderItem.from("초코케이크", 1),
                                OrderItem.from("아이스크림", 2),
                                OrderItem.from("티본스테이크", 10)
                        ),
                        0
                ),
                Arguments.of(
                        2,
                        List.of(
                                OrderItem.from("초코케이크", 1),
                                OrderItem.from("아이스크림", 2),
                                OrderItem.from("티본스테이크", 10)
                        ),
                        0
                ),
                Arguments.of(
                        3,
                        List.of(
                                OrderItem.from("초코케이크", 1),
                                OrderItem.from("아이스크림", 2),
                                OrderItem.from("티본스테이크", 10)
                        ),
                        2023 * 3
                )
        );
    }
}
