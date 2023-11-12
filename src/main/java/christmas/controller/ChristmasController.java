package christmas.controller;

import christmas.domain.ChristmasService;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import christmas.domain.event.Benefit;
import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.EventManager;
import christmas.domain.event.PromotionEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;
import christmas.dto.BenefitDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.PromotionMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final EventManager eventManager = EventManager.from(
            List.of(new ChristmasDDayEvent(),
                    new WeekdayEvent(),
                    new WeekendEvent(),
                    new SpecialEvent(),
                    new PromotionEvent()));
    private final ChristmasService christmasService = new ChristmasService();

    public void run() {
        outputView.printWelcome();

        int dayOfMonth = inputView.getVisitDate();
        VisitDate visitDate = VisitDate.from(dayOfMonth);

        List<OrderMenuDto> orderMenuDtos = inputView.getOrderItems();
        List<OrderMenu> orderMenus = orderMenuDtos.stream()
                .map(orderItem -> OrderMenu.from(orderItem.menuName(), orderItem.quantity()))
                .toList();
        Order order = Order.from(orderMenus);

        outputView.printEventPreview(dayOfMonth);

        outputView.printOrderItems(orderMenuDtos);

        int totalOrderAmount = order.getTotalOrderAmount();
        outputView.printTotalOrderAmount(totalOrderAmount);

        List<Benefit> benefits = eventManager.getBenefits(visitDate, order);

        // 증정된 메뉴 가져오기
        Map<String, Integer> promotionMenus = benefits.stream()
                .map(Benefit::getPromotionMenus)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Menu::getName, Collectors.summingInt(unused -> 1)));

        List<PromotionMenuDto> promotionMenuDtos = promotionMenus.entrySet().stream()
                .map(entry -> new PromotionMenuDto(entry.getKey(), entry.getValue()))
                .toList();

        outputView.printPromotionMenu(promotionMenuDtos);

        List<BenefitDto> benefitDtos = new ArrayList<>();
        for (Benefit benefit : benefits) {
            String eventName = benefit.getEventName();
            int discountAmount = benefit.getBenefitAmount();
            benefitDtos.add(new BenefitDto(eventName, discountAmount));
        }

        outputView.printBenefitDetails(benefitDtos);

        int totalBenefitAmount = 0;
        for (Benefit benefit : benefits) {
            totalBenefitAmount += benefit.getBenefitAmount();
        }

        outputView.printTotalBenefitAmount(totalBenefitAmount);

        int totalDiscountAmount = 0;
        for (Benefit benefit : benefits) {
            totalDiscountAmount += benefit.getDiscountAmount();
        }

        outputView.printPaymentAmount(totalOrderAmount - totalDiscountAmount);

        String eventBadge = christmasService.getEventBadge(totalBenefitAmount);

        outputView.printEventBadge(eventBadge);
    }
}
