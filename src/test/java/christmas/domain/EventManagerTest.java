package christmas.domain;

import christmas.domain.EventManager;
import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.PromotionEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
class EventManagerTest {
    private static final EventManager eventManager = EventManager.from(
            List.of(new ChristmasDDayEvent(), new WeekdayEvent(), new WeekendEvent(), new SpecialEvent(),
                    new PromotionEvent()));

    //TODO 혜택 내역 생성 테스트 작성하기
}
