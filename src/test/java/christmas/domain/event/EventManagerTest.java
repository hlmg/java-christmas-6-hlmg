package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class EventManagerTest {
    private static final EventManager eventManager = EventManager.from(
            List.of(new ChristmasDDayEvent(), new WeekdayEvent(), new WeekendEvent(), new SpecialEvent(),
                    new PromotionEvent()));

    @ParameterizedTest
    @MethodSource
    void 적용된_이벤트_목록을_가져올_수_있다(int dayOfMonth, Class<Event>[] expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Order order = Order.from(List.of(OrderMenu.from("양송이수프", 20)));
        List<Event> appliedEvents = eventManager.getAppliedEvents(visitDate, order);

        assertThat(appliedEvents).hasExactlyElementsOfTypes(expected);
    }

    public static Stream<Arguments> 적용된_이벤트_목록을_가져올_수_있다() {
        return Stream.of(
                Arguments.of(1,
                        new Class[]{ChristmasDDayEvent.class, PromotionEvent.class}),
                Arguments.of(25,
                        new Class[]{ChristmasDDayEvent.class, SpecialEvent.class,
                                PromotionEvent.class}),
                Arguments.of(26,
                        new Class[]{PromotionEvent.class})
        );
    }

    //TODO 혜택 내역 생성 테스트 작성하기
}
