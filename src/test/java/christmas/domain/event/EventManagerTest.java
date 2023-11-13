package christmas.domain.event;

import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
class EventManagerTest {
    private static final EventManager eventManager = EventManager.from(
            List.of(new ChristmasDDayEvent(), new WeekdayEvent(), new WeekendEvent(), new SpecialEvent(),
                    new PromotionEvent()));

    //TODO 혜택 내역 생성 테스트 작성하기
}
