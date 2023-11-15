package christmas.common;

import christmas.controller.ChristmasController;
import christmas.domain.ChristmasService;
import christmas.domain.EventManager;
import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.PromotionEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class Config {

    public ChristmasController getChristmasController() {
        return new ChristmasController(new InputView(), new OutputView(), new ChristmasService(getEventManager()));
    }

    private EventManager getEventManager() {
        return EventManager.from(
                List.of(new ChristmasDDayEvent(), new WeekdayEvent(), new WeekendEvent(), new SpecialEvent(),
                        new PromotionEvent()));
    }
}
