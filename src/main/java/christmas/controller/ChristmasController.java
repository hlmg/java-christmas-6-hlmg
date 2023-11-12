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
        Order order = orderFrom(orderMenuDtos);

        outputView.printEventPreview(dayOfMonth);

        outputView.printOrderItems(orderMenuDtos);

        outputView.printTotalOrderAmount(order.getTotalOrderAmount());

        AppliedBenefits appliedBenefits = christmasService.plan(visitDate, order);

        outputView.printPromotionMenu(promotionMenuDtosFrom(appliedBenefits));

        outputView.printBenefits(benefitDtosFrom(appliedBenefits));

        outputView.printTotalBenefitAmount(appliedBenefits.getTotalBenefitAmount());

        int totalDiscountAmount = appliedBenefits.getTotalDiscountAmount();
        outputView.printPaymentAmount(order.getTotalOrderAmount() - totalDiscountAmount);

        outputView.printEventBadge(christmasService.getEventBadge(appliedBenefits.getTotalBenefitAmount()));
    }

    private Order orderFrom(List<OrderMenuDto> orderMenuDtos) {
        List<OrderMenu> orderMenus = orderMenuDtos.stream()
                .map(orderItem -> OrderMenu.from(orderItem.menuName(), orderItem.quantity()))
                .toList();
        return Order.from(orderMenus);
    }

    private List<PromotionMenuDto> promotionMenuDtosFrom(AppliedBenefits appliedBenefits) {
        Map<String, Integer> promotionMenus = appliedBenefits.getPromotionMenus();
        return promotionMenus.entrySet().stream()
                .map(entry -> new PromotionMenuDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    private List<BenefitDto> benefitDtosFrom(AppliedBenefits appliedBenefits) {
        List<Benefit> benefits = appliedBenefits.getBenefits();
        return benefits.stream()
                .map(benefit -> new BenefitDto(benefit.getEventName(), benefit.getBenefitAmount()))
                .toList();
    }
}
