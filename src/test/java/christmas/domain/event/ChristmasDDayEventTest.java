package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Benefit.Benefit;
import christmas.domain.Benefit.DiscountBenefit;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class ChristmasDDayEventTest {

    /*
    - 크리스마스 디데이 할인
    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
     */
    @ParameterizedTest
    @CsvSource(textBlock = """
            1, true
            25, true
            26, false
            """)
    void 크리스마스_디데이_이벤트_조건_테스트(int dayOfMonth, boolean expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderMenu.from("타파스", 2)));

        boolean actual = new ChristmasDDayEvent().isSatisfiedBy(visitDate, order);

        assertThat(actual).isEqualTo(expected);
    }

    /*
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - 총주문 금액에서 해당 금액만큼 할인
    (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
    */
    @ParameterizedTest
    @CsvSource(textBlock = """
            1, 1000
            2, 1100
            """)
    void 크리스마스_디데이_이벤트_혜택_테스트(int dayOfMonth, int expectedDiscountAmount) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderMenu.from("타파스", 2)));

        Benefit actual = new ChristmasDDayEvent().getBenefitFrom(visitDate, order);

        assertThat(actual).isEqualTo(DiscountBenefit.from("크리스마스 디데이 할인", expectedDiscountAmount));
    }
}
