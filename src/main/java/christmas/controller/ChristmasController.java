package christmas.controller;

import christmas.domain.ChristmasService;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import christmas.domain.event.AppliedBenefits;
import christmas.domain.event.Benefit;
import christmas.dto.BenefitDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.PromotionMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
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

        AppliedBenefits appliedBenefits = christmasService.plan(visitDate, order);

        Map<String, Integer> promotionMenus = appliedBenefits.getPromotionMenus();

        List<PromotionMenuDto> promotionMenuDtos = promotionMenus.entrySet().stream()
                .map(entry -> new PromotionMenuDto(entry.getKey(), entry.getValue()))
                .toList();

        outputView.printPromotionMenu(promotionMenuDtos);

        List<Benefit> benefits = appliedBenefits.getBenefits();

        List<BenefitDto> benefitDtos = new ArrayList<>();
        for (Benefit benefit : benefits) {
            String eventName = benefit.getEventName();
            int discountAmount = benefit.getBenefitAmount();
            benefitDtos.add(new BenefitDto(eventName, discountAmount));
        }
        outputView.printBenefits(benefitDtos);

        int totalBenefitAmount = appliedBenefits.getTotalBenefitAmount();

        outputView.printTotalBenefitAmount(totalBenefitAmount);

        int totalDiscountAmount = appliedBenefits.getTotalDiscountAmount();

        outputView.printPaymentAmount(totalOrderAmount - totalDiscountAmount);

        String eventBadge = christmasService.getEventBadge(totalBenefitAmount);

        outputView.printEventBadge(eventBadge);
    }
}
