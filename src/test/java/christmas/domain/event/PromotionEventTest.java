package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Benefit.Benefit;
import christmas.domain.Benefit.PromotionBenefit;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class PromotionEventTest {

    // 할인 전 총주문 금액이 12만 원 이상
    @ParameterizedTest
    @CsvSource(textBlock = """
            양송이수프, 19, false
            양송이수프, 20, true
            """)
    void 증정_이벤트_조건_테스트(String menuName, int quantity, boolean expected) {
        VisitDate visitDate = VisitDate.from(1);
        Order order = Order.from(List.of(OrderMenu.from(menuName, quantity)));
        PromotionEvent promotionEvent = new PromotionEvent();
        boolean actual = promotionEvent.isSatisfiedCondition(visitDate, order);

        assertThat(actual).isEqualTo(expected);
    }

    // 샴페인 1개 증정
    @Test
    void 증정_이벤트_혜택_테스트() {
        VisitDate visitDate = VisitDate.from(1);
        Order order = Order.from(List.of(OrderMenu.from("양송이수프", 1)));

        Benefit actual = new PromotionEvent().getBenefitFrom(visitDate, order);

        assertThat(actual).isEqualTo(PromotionBenefit.from("증정 이벤트", Menu.CHAMPAGNE));
    }
}
