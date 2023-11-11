package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
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
class PromotionEventTest {

    // 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
    @ParameterizedTest
    @MethodSource()
    void 증정_이벤트_혜택_테스트(String menuName, int quantity, List<Menu> expected) {
        VisitDate visitDate = VisitDate.from(1);
        Order order = Order.from(List.of(OrderItem.from(menuName, quantity)));
        Plan plan = Plan.from(visitDate, order);

        Benefit actual = new PromotionEvent().getBenefitFrom(plan);

        assertThat(actual).isEqualTo(Benefit.from(0, expected));
    }

    public static Stream<Arguments> 증정_이벤트_혜택_테스트() {
        return Stream.of(
                Arguments.of("양송이수프", 19, List.of()),
                Arguments.of("양송이수프", 20, List.of(Menu.CHAMPAGNE))
        );
    }
}
